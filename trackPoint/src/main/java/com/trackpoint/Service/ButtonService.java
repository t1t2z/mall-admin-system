package com.trackpoint.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trackpoint.Entity.Button;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ButtonService extends IService<Button> {
    int LookA();
    int LookB();
    int LookC();
    int SalesA();
    int SalesB();
    int SalesC();
    List<Map<String, Object>> getButtonCountByTimeRange(String startTime, String endTime);
    IPage<Map<String, Object>> pageCC(Page<Button> page,
                        Wrapper<Button> queryWrapper,
                        String startTime, 
                        String endTime);
    List<Map<String, Object>> getDailyButtonCounts(String startTime, String endTime);
}