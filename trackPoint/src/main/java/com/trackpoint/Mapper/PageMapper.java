package com.trackpoint.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trackpoint.Entity.Page;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PageMapper extends BaseMapper<Page> {
    @Select("SELECT COUNT(p.pagePath) FROM page p " +
           "LEFT JOIN event e ON e.eventId = p.page " +
           "WHERE p.pagePath = #{page} " +
           "AND e.timestamp >= STR_TO_DATE(#{startTime}, '%Y-%m-%d %H:%i:%s') " +
           "AND e.timestamp <= STR_TO_DATE(#{endTime}, '%Y-%m-%d %H:%i:%s')")
    int countPage(@Param("page") String page,
                                @Param("startTime") String startTime, 
                                @Param("endTime") String endTime);
}