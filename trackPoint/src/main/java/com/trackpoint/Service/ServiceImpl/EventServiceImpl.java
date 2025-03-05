package com.trackpoint.Service.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trackpoint.Entity.Event;
import com.trackpoint.Mapper.EventMapper;
import com.trackpoint.Service.EventService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {
    @Resource
    private EventMapper eventMapper;
    public Integer saveEvent(Event event) {
        eventMapper.insertEvent(event); // 保存 Event
        return event.getEventId(); // 返回生成的 eventId
    }

    @Override
    public int countUserIds() {
        return eventMapper.countUserIds();
    }

}
