package com.trackpoint.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName page
*/
public class Page implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Integer page;
    /**
    * 
    */
    @NotBlank(message="[]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max= 255,message="编码长度不能超过255")
    private String pagePath;
    /**
    * 
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max= 255,message="编码长度不能超过255")
    private String referrer;



    /**
    * 
    */
    public void setPage(Integer page){
    this.page = page;
    }

    /**
    * 
    */
    public void setPagePath(String pagePath){
    this.pagePath = pagePath;
    }

    /**
    * 
    */
    public void setReferrer(String referrer){
    this.referrer = referrer;
    }


    /**
    * 
    */
    public Integer getPage(){
    return this.page;
    }

    /**
    * 
    */
    public String getPagePath(){
    return this.pagePath;
    }

    /**
    * 
    */
    public String getReferrer(){
    return this.referrer;
    }

    public void setSize(int pageSize) {

    }

    public void setCurrent(int pageNum) {

    }
}
