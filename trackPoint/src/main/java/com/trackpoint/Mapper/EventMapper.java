package com.trackpoint.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trackpoint.Entity.Event;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EventMapper extends BaseMapper<Event> {
    @Insert("INSERT INTO event (userId, browser, version, timestamp,type) VALUES (#{userId}, #{browser}, #{version}, #{timestamp},#{type})")
    @Options(useGeneratedKeys = true, keyProperty = "eventId") // 获取生成的主键
    void insertEvent(Event event);

    // 获取去重的 userId 数量
    @Select("SELECT COUNT(DISTINCT userId) FROM event")
    int countUserIds();

    @Select("SELECT * FROM event WHERE buttonId = #{buttonId}")
    List<Event> selectByButtonId(Integer buttonId);
}
