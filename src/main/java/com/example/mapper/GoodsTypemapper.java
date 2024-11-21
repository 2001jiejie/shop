package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.Map;

public class GoodsTypemapper {
    private static final Map<String,Integer> typeMap = new HashMap();
    static{
        typeMap.put("电子产品",1);
        typeMap.put("服装",2);
        typeMap.put("家具",3);
        typeMap.put("书籍",4);
    }
    public static Integer getTypeId(String typeName){
        return typeMap.get(typeName);
    }
}
