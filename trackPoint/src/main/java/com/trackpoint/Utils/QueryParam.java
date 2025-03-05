package com.trackpoint.Utils;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryParam {
    public String eventName;

    public HashMap param=new HashMap();

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public HashMap getParam() {
        return param;
    }

    public void setParam(HashMap param) {
        this.param = param;
    }
}
