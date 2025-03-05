package com.trackpoint.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName form
*/
public class Form implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Integer formId;
    /**
    * 0没通过1通过
    */
    @ApiModelProperty("0没通过1通过")
    private Integer valid;
    /**
    * 
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max= 255,message="编码长度不能超过255")
    private String form;


    /**
    * 
    */
    public void setFormId(Integer formId){
    this.formId = formId;
    }

    /**
    * 0没通过1通过
    */
    public void setValid(Integer valid){
    this.valid = valid;
    }

    /**
    * 
    */
    public void setForm(String form){
    this.form = form;
    }


    /**
    * 
    */
    public Integer getFormId(){
    return this.formId;
    }

    /**
    * 0没通过1通过
    */
    public Integer getValid(){
    return this.valid;
    }

    /**
    * 
    */
    public String getForm(){
    return this.form;
    }

}
