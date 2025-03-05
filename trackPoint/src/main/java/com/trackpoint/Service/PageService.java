package com.trackpoint.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trackpoint.Entity.Page;

import org.springframework.stereotype.Service;

@Service
public interface PageService extends IService<Page> {

    int getPageCount(String page, String startTime, String endTime);
}