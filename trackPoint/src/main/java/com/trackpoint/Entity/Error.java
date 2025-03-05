package com.trackpoint.Entity;


import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName error
*/
public class Error implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Integer errorId;
    /**
    * 
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max= 255,message="编码长度不能超过255")
    private String errorMessage;
    /**
    * 
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max= 255,message="编码长度不能超过255")
    private String errorStack;

    /**
    * 
    */
    public void setErrorId(Integer errorId){
    this.errorId = errorId;
    }

    /**
    * 
    */
    public void setErrorMessage(String errorMessage){
    this.errorMessage = errorMessage;
    }

    /**
    * 
    */
    public void setErrorStack(String errorStack){
    this.errorStack = errorStack;
    }


    /**
    * 
    */
    public Integer getErrorId(){
    return this.errorId;
    }

    /**
    * 
    */
    public String getErrorMessage(){
    return this.errorMessage;
    }

    /**
    * 
    */
    public String getErrorStack(){
    return this.errorStack;
    }

}
