package com.trackpoint.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.trackpoint.Entity.Point;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PointMapper extends BaseMapper<Point> {
    @Insert("INSERT INTO point (eventname, description, location, creattime, `trigger`) " +
            "VALUES (#{eventname}, #{description}, #{location}, #{creattime}, #{trigger})")
    int insertPoint(Point point);

    @Delete("DELETE FROM point WHERE eventname = #{eventname}")
    int deletePoint(String eventname);

    @Update("UPDATE point SET description = #{description}, location = #{location}, " +
            "creattime = #{creattime}, `trigger` = #{trigger} WHERE eventname = #{eventname}")
    int updatePoint(Point point);

    @Select("SELECT * FROM point WHERE eventname = #{eventname}")
    Point selectByEventName(String eventname);

    @Select("SELECT * FROM point ${ew.customSqlSegment}")
    public IPage<Point> pageCC(IPage page, @Param(Constants.WRAPPER) Wrapper<Point> wrapper);

} 