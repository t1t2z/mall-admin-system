package com.trackpoint.Mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.trackpoint.Entity.Button;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ButtonMapper extends BaseMapper<Button> {
    // 获取购买数量A
    @Select("SELECT COUNT(button) FROM button where button=1")
    int countGoodsA();
    // 获取购买数量B
    @Select("SELECT COUNT(button) FROM button where button=2")
    int countGoodsB();
    // 获取购买数量C
    @Select("SELECT COUNT(button) FROM button where button=3")
    int countGoodsC();
    // 获取购买数量A
    @Select("SELECT COUNT(button) FROM button where button=4")
    int countA();
    // 获取购买数量B
    @Select("SELECT COUNT(button) FROM button where button=5")
    int countB();
    // 获取购买数量C
    @Select("SELECT COUNT(button) FROM button where button=6")
    int countC();

    @Select("SELECT b.button as buttonType, COUNT(*) as count " +
            "FROM button b " +
            "LEFT JOIN event e ON e.eventId = b.buttonId " +
            "WHERE e.timestamp BETWEEN #{startTime} AND #{endTime} " +
            "GROUP BY b.button " +
            "ORDER BY buttonType")
    List<Map<String, Object>> countButtonsByTypeAndTime(@Param("startTime") String startTime,
                                @Param("endTime") String endTime);

    @Select("SELECT * FROM button b " +
    "LEFT JOIN event e ON e.eventId = b.buttonId " +
    "${ew.customSqlSegment} AND e.timestamp BETWEEN #{startTime} AND #{endTime}")
    public IPage<Map<String, Object>> pageCC(IPage<Button> page,
                                             @Param(Constants.WRAPPER) Wrapper<Button> wrapper,
                                             @Param("startTime") String startTime,
                                             @Param("endTime") String endTime);

    @Select("SELECT DATE(e.timestamp) as date, b.button as buttonType, COUNT(*) as count " +
           "FROM button b " +
           "LEFT JOIN event e ON e.eventId = b.buttonId " +
           "WHERE e.timestamp BETWEEN #{startTime} AND #{endTime} " +
           "GROUP BY DATE(e.timestamp), b.button " +
           "ORDER BY date, buttonType")
    List<Map<String, Object>> countDailyButtonTypes(@Param("startTime") String startTime, 
                                                   @Param("endTime") String endTime);
}                        