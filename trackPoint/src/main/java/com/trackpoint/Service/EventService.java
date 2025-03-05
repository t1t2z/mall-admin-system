package com.trackpoint.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trackpoint.Entity.Event;

import org.springframework.stereotype.Service;

@Service
public interface EventService extends IService<Event> {
    public Integer saveEvent(Event event);
    public int countUserIds();
}
