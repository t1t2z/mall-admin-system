package com.trackpoint.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trackpoint.Entity.Button;
import com.trackpoint.Mapper.ButtonMapper;
import com.trackpoint.Service.ButtonService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

@Service
public class ButtonServiceImpl extends ServiceImpl<ButtonMapper, Button> implements ButtonService {
    @Resource
    private ButtonMapper buttonMapper;

    public int LookA(){
        return buttonMapper.countA();
    }
    public int LookB(){
        return buttonMapper.countB();
    }
    public int LookC(){
        return buttonMapper.countC();
    }
    public int SalesA(){
        return buttonMapper.countGoodsA();
    }
    public int SalesB(){
        return buttonMapper.countGoodsB();
    }
    public int SalesC(){
        return buttonMapper.countGoodsC();
    }

    @Override
    public List<Map<String, Object>> getButtonCountByTimeRange(String startTime, String endTime) {
        return buttonMapper.countButtonsByTypeAndTime(startTime, endTime);
    }

    @Override
    public IPage<Map<String, Object>> pageCC(Page<Button> page,
                              Wrapper<Button> wrapper,
                              String startTime, 
                              String endTime) {
        return buttonMapper.pageCC(page, wrapper, startTime, endTime);
    }

    @Override
    public List<Map<String, Object>> getDailyButtonCounts(String startTime, String endTime) {
        return buttonMapper.countDailyButtonTypes(startTime, endTime);
    }
}