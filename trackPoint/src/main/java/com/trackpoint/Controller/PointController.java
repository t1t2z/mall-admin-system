package com.trackpoint.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trackpoint.Entity.Point;
import com.trackpoint.Service.PointService;
import com.trackpoint.Utils.QueryPageParam;
import com.trackpoint.Utils.Results;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/point")
public class PointController {
    @Resource
    private PointService pointService;

    @PostMapping("/add")
    public ResponseEntity<String> addPoint(@RequestBody Point point) {
//        if (point.getCreattime() == null) {
//            point.setCreattime(LocalDateTime.now());  // 如果没有传入时间，设置为当前时间
//        }
//        pointService.save(point);
//        return Results.success();

         return pointService.addPoint(point)
             ? ResponseEntity.ok("添加成功")
             : ResponseEntity.badRequest().body("添加失败");
    }

    @DeleteMapping("/delete/{eventname}")
    public ResponseEntity<String> deletePoint(@PathVariable String eventname) {
        return pointService.removePoint(eventname) 
            ? ResponseEntity.ok("删除成功") 
            : ResponseEntity.badRequest().body("删除失败");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePoint(@RequestBody Point point) {
        return pointService.modifyPoint(point) 
            ? ResponseEntity.ok("更新成功") 
            : ResponseEntity.badRequest().body("更新失败");
    }

    @GetMapping("/get/{eventname}")
    public ResponseEntity<?> getPoint(@PathVariable String eventname) {
        Point point = pointService.getPointByEventName(eventname);
        return point != null 
            ? ResponseEntity.ok(point) 
            : ResponseEntity.badRequest().body("未找到该埋点");
    }

    @PostMapping("/listPage")
    public Results listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String eventname = (String) param.get("eventname");
        String description=(String) param.get("description");
        String trigger=(String) param.get("trigger");
        String creatTime=(String) param.get("creatTime");

        Page<Point> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Point> queryWrapper = new LambdaQueryWrapper<>();
        if (eventname != null && !eventname.isEmpty()) {
            queryWrapper.eq(Point::getEventname, eventname);
        }
        if(description != null && !description.isEmpty()){
            queryWrapper.like(Point::getDescription, description);
        }
        if (trigger != null && !trigger.isEmpty()){
            queryWrapper.eq(Point::getTrigger, trigger);
        }
        if (creatTime != null && !creatTime.isEmpty()) {
            // 将 creatTime 转换为 LocalDate
            LocalDate date = LocalDate.parse(creatTime);
            // 设置开始时间和结束时间
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

            // 添加条件，筛选出在同一天的记录
            queryWrapper.between(Point::getCreattime, startOfDay, endOfDay);
        }

        IPage result = pointService.pageCC(page, queryWrapper);
        return Results.success(result.getRecords(), result.getTotal());
    }
} 