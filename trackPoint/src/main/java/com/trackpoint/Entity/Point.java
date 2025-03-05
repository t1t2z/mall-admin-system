package com.trackpoint.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;

/**
* 
* @TableName point
*/
public class Point implements Serializable {

    /**
    * 
    */
    @NotBlank(message="[]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max= 255,message="编码长度不能超过255")
    private String eventname;
    /**
    * 
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max= 255,message="编码长度不能超过255")
    private String description;
    /**
    * 
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max= 255,message="编码长度不能超过255")
    private String location;
    /**
    * 
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("")
    private LocalDateTime creattime;
    /**
    * 
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max= 255,message="编码长度不能超过255")
    @TableField("`trigger`")
    private String trigger;

    /**
    * 
    */
    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    /**
    * 
    */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
    * 
    */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
    * 
    */
    public void setCreattime(LocalDateTime creattime) {
        this.creattime = creattime;
    }

    /**
    * 
    */
    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }


    /**
    * 
    */
    public String getEventname() {
        return this.eventname;
    }

    /**
    * 
    */
    public String getDescription() {
        return this.description;
    }

    /**
    * 
    */
    public String getLocation() {
        return this.location;
    }

    /**
    * 
    */
    public LocalDateTime getCreattime() {
        return this.creattime;
    }

    /**
    * 
    */
    public String getTrigger() {
        return this.trigger;
    }

}
