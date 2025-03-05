package com.trackpoint.Service.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trackpoint.Entity.Form;
import com.trackpoint.Mapper.FormMapper;
import com.trackpoint.Service.FormService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl extends ServiceImpl<FormMapper, Form> implements FormService {
    @Resource
    private FormMapper formMapper;
}