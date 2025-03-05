package com.trackpoint.Utils;

import lombok.Data;

@Data
public class Results {
    private int code;
    private String msg;
    private Object data;
    private Long total;

    public static Results fail(){return result(400,"失败",null,0L);}
    public static Results success(){return result(200,"成功",null,0L);}
    public static Results success(Object data) {return result(200,"成功",data,0L);}
    public static Results success(Object data,Long total) {return result(200,"成功",data,total);}

    private static Results result(int code, String msg, Object data, Long total) {
        Results result = new Results();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        result.setTotal(total);
        return result;
    }
}

