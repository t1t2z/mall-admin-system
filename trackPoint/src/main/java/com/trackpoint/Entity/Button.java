package com.trackpoint.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName button
*/
public class Button implements Serializable{

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Integer buttonId;
    /**
    * 按钮名
    */
    @NotBlank(message="[按钮名]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("按钮名")
    @Length(max= 255,message="编码长度不能超过255")
    private String button;
    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Integer positionX;
    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Integer positionY;

    /**
    * 
    */
    public void setButtonId(Integer buttonId){
    this.buttonId = buttonId;
    }

    /**
    * 按钮名
    */
    public void setButton(String button){
    this.button = button;
    }

    /**
    * 
    */
    public void setPositionX(Integer positionX){
    this.positionX = positionX;
    }

    /**
    * 
    */
    public void setPositionY(Integer positionY){
    this.positionY = positionY;
    }


    /**
    * 
    */
    public Integer getButtonId(){
    return this.buttonId;
    }

    /**
    * 按钮名
    */
    public String getButton(){
    return this.button;
    }

    /**
    * 
    */
    public Integer getPositionX(){
    return this.positionX;
    }

    /**
    * 
    */
    public Integer getPositionY(){
    return this.positionY;
    }

}
