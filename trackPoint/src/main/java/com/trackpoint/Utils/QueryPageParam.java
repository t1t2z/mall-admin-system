package com.trackpoint.Utils;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    //默认
    private static int PAGE_SIZE = 10;
    private static int PAGE_NUM = 1;

    public int PageSize;
    public int PageNum;

    public HashMap param=new HashMap();

}
