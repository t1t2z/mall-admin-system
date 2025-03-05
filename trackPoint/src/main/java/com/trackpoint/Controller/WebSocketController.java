package com.trackpoint.Controller;

import jakarta.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.trackpoint.Service.ButtonService;
import com.trackpoint.Service.EventService;
import com.trackpoint.Service.PageService;
import com.trackpoint.Utils.Results;

import java.util.HashMap;

@RestController
public class WebSocketController {
    @Resource
    private SimpMessagingTemplate messagingTemplate;
    @Resource
    private ButtonService buttonService;
    @Resource
    private EventService eventService;
    @Resource
    private PageService pageService;

    @MessageMapping("/menber")  // 路径与前端发送的消息路径匹配
    @SendTo("/topic/public")  // 广播到所有订阅了 /topic/public 的客户端
    public int menbercount() {
        try{
          return eventService.countUserIds();  
        }catch (Exception e) {
            System.out.println("Error in sales: " + e.getMessage());
            return 400; // 返回错误码
        }
    }

    @MessageMapping("/sales")  // 路径与前端发送的消息路径匹配
    @SendTo("/topic/public")  // 广播到所有订阅了 /topic/public 的客户端
    public Results sales(HashMap<String, Object> message) {
        try {
//            Integer buttonType = (Integer) message.get("buttonType");
            String startTime = (String) message.get("startTime");
            String endTime = (String) message.get("endTime");
            return Results.success(buttonService.getButtonCountByTimeRange(startTime, endTime));
        } catch (Exception e) {
            System.out.println("Error in sales: " + e.getMessage());
            return Results.fail(); // 返回错误码
        }
    }

    @MessageMapping("/look")
    @SendTo("/topic/public")
    public int look(HashMap<String, Object> message) {
        try {
            String page=(String) message.get("page");
            String startTime = (String) message.get("startTime");
            String endTime = (String) message.get("endTime");
            System.out.println(message);
            return pageService.getPageCount(page, startTime, endTime);
        } catch (Exception e) {
            System.out.println("Error in sales: " + e.getMessage());
            return 400; // 返回错误码
        }
    }
}
