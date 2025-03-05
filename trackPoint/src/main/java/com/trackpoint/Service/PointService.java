package com.trackpoint.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trackpoint.Entity.Point;
import org.springframework.stereotype.Service;

@Service
public interface PointService extends IService<Point> {
    boolean addPoint(Point point);
    boolean removePoint(String eventname);
    boolean modifyPoint(Point point);
    Point getPointByEventName(String eventname);
    
    IPage<Point> pageCC(Page<Point> page, 
                       LambdaQueryWrapper<Point> wrapper);
} 