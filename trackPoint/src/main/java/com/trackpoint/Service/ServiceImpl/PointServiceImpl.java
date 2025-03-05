package com.trackpoint.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trackpoint.Entity.Point;
import com.trackpoint.Mapper.PointMapper;
import com.trackpoint.Service.PointService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements PointService {
    @Resource
    private PointMapper pointMapper;

    @Override
    public boolean addPoint(Point point) {
        return pointMapper.insertPoint(point) > 0;
    }

    @Override
    public boolean removePoint(String eventname) {
        return pointMapper.deletePoint(eventname) > 0;
    }

    @Override
    public boolean modifyPoint(Point point) {
        return pointMapper.updatePoint(point) > 0;
    }

    @Override
    public Point getPointByEventName(String eventname) {
        return pointMapper.selectByEventName(eventname);
    }

    @Override
    public IPage<Point> pageCC(Page<Point> page,
                              LambdaQueryWrapper<Point> wrapper) {
        return pointMapper.pageCC(page, wrapper);
    }
} 