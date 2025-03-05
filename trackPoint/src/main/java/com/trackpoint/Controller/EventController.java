package com.trackpoint.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.trackpoint.Service.EventService;
import com.trackpoint.Service.ErrorService;
import com.trackpoint.Entity.Button;
import com.trackpoint.Entity.Error;
import com.trackpoint.Entity.Event;
import com.trackpoint.Entity.Form;
import com.trackpoint.Entity.Page;
import com.trackpoint.Service.ButtonService;
import com.trackpoint.Service.FormService;
import com.trackpoint.Service.PageService;
import com.trackpoint.Utils.QueryPageParam;
import com.trackpoint.Utils.QueryParam;
import com.trackpoint.Utils.Results;
import jakarta.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {
    @Resource
    private EventService eventService;
    @Resource
    private ErrorService errorService;
    @Resource
    private ButtonService buttonService;
    @Resource
    private FormService formService;
    @Resource
    private PageService pageService;
    

    @PostMapping("/track")
    public int trackPage(@RequestBody QueryParam queryParam) {
        HashMap param = queryParam.getParam();
        String browser=(String)param.get("browser");
        Integer userId=(Integer)param.get("userId");
        String time=(String)param.get("timestamp");
        String version=(String)param.get("version");
        // 添加空值检查
        if (userId == null || browser == null || time == null || version == null) {
            return 400; // 返回错误代码
        }
        if(browser.equals(null)||userId==null||time==null||version==null) return 400;
        Event e=new Event(userId,browser,version,time);
        Integer eventId=0;
        switch (queryParam.getEventName()) {
            case "page":e.setType(1);
            String pagePath=(String)param.get("pagePath");
            String referrer=(String)param.get("referrer");
            eventService.saveEvent(e); // 保存 Event
            eventId = e.getEventId(); // 获取生成的 eventId
            Page p=new Page();
            p.setPage(eventId);// 传递 eventId
            p.setReferrer(referrer);
            p.setPagePath(pagePath);
            pageService.save(p); // 保存page
            return 200;
            case "error":e.setType(3);
            Error error=new Error();
            eventService.saveEvent(e); // 保存 Event
            error.setErrorId(e.getEventId()); // 传递 eventId
            error.setErrorMessage((String)param.get("errorMessage")); // 设置错误信息
            error.setErrorStack((String)param.get("errorStack"));
            errorService.save(error); // 保存错误信息
            return 200;
            case "form":e.setType(2);
            eventService.saveEvent(e); // 保存 Event
            Form form=new Form();
            form.setFormId(e.getEventId()); // 传递 eventId
            form.setForm((String)param.get("form")); // 设置表单数据
            form.setValid((Integer)param.get("valid"));
            formService.save(form); // 保存表单信息
            return 200;
            case "button":e.setType(0);
            eventService.saveEvent(e); // 保存 Event
            Button button=new Button();
            button.setButtonId(e.getEventId()); // 传递 eventId
            button.setButton((String)param.get("button"));
            button.setPositionX((Integer)param.get("positionX"));
            button.setPositionY((Integer)param.get("positionY"));
            buttonService.save(button); // 保存按钮信息
            return 200;
            default:return 400;
        }
    }

    @PostMapping("/update")
    public int update(@RequestBody Event event){
        System.out.println("eventId:"+event.getEventId());
        System.out.println("event:"+event.getTimestamp());
        return eventService.updateById(event)?200:400;
    }

    @PostMapping("/listPage")
    public Results listPage(@RequestBody QueryPageParam query) {
        HashMap param=query.getParam();
        String type = (String) param.get("type");
        String startTime = (String) param.get("startTime");
        String endTime = (String) param.get("endTime");

        com.baomidou.mybatisplus.extension.plugins.pagination.Page page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Button> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(type) && !"null".equals(type)) {
            queryWrapper.eq(Button::getButton, type);
        }
        IPage result = buttonService.pageCC(page, queryWrapper, startTime, endTime); // 传递 startTime 和 endTime

        return Results.success(result.getRecords(), result.getTotal());
    }
    
    @GetMapping("/daily-counts")
    public Results getDailyButtonCounts(@RequestParam String startTime, 
                                      @RequestParam String endTime) {
        List<Map<String, Object>> result = buttonService.getDailyButtonCounts(startTime, endTime);
        return Results.success(result);
    }
}
