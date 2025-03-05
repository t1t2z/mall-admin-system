package com.trackpoint.Service.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trackpoint.Entity.Error;
import com.trackpoint.Mapper.ErrorMapper;
import com.trackpoint.Service.ErrorService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ErrorServiceImpl extends ServiceImpl<ErrorMapper, Error> implements ErrorService {
    @Resource
    private ErrorMapper errorMapper;

} 