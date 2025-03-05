package com.trackpoint.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName event
*/
public class Event implements Serializable {

    /**
    * 主键，事件标识符
    */
    @NotNull(message="[主键，事件标识符]不能为空")
    @ApiModelProperty("主键，事件标识符")
    @TableId(value = "EventId", type = IdType.AUTO)
    private Integer EventId;
    /**
    * 用户ID
    */
    @NotNull(message="[用户ID]不能为空")
    @ApiModelProperty("用户ID")
    private Integer userId;
    /**
    * 操作系统
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("操作系统")
    @Length(max= 255,message="编码长度不能超过255")
    private String browser;
    /**
    * 版本号
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("版本号")
    @Length(max= 255,message="编码长度不能超过255")
    private String version;
    /**
    * 时间戳
    */
    @NotBlank(message="[时间戳]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("时间戳")
    @Length(max= 255,message="编码长度不能超过255")
    private String timestamp;
    /**
    * 0button,1page,2form,3erroe
    */
    @ApiModelProperty("0button,1page,2form,3erroe")
    private Integer type;

    /**
    * 主键，事件标识符
    */
    public void setEventId(Integer EventId){
    this.EventId = EventId;
    }

    /**
    * 用户ID
    */
    public void setUserId(Integer userId){
    this.userId = userId;
    }

    /**
    * 操作系统
    */
    public void setBrowser(String browser){
    this.browser = browser;
    }

    /**
    * 版本号
    */
    public void setVersion(String version){
    this.version = version;
    }

    /**
    * 时间戳
    */
    public void setTimestamp(String timestamp){
    this.timestamp = timestamp;
    }

    /**
    * 0button,1page,2form,3erroe
    */
    public void setType(Integer type){
    this.type = type;
    }


    /**
    * 主键，事件标识符
    */
    public Integer getEventId(){
    return this.EventId;
    }


    /**
    * 用户ID
    */
    public Integer getUserId(){
    return this.userId;
    }

    /**
    * 操作系统
    */
    public String getBrowser(){
    return this.browser;
    }

    /**
    * 版本号
    */
    public String getVersion(){
    return this.version;
    }

    /**
    * 时间戳
    */
    public String getTimestamp(){
    return this.timestamp;
    }

    /**
    * 0button,1page,2form,3erroe
    */
    public Integer getType(){
    return this.type;
    }

    public Event(){

    }

    public Event(Integer userId, String browser, String version, String timestamp){
        this.userId = userId;
        this.browser = browser;
        this.version = version;
        this.timestamp = timestamp;
    }


}
