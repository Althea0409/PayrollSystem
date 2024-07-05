package com.lsu.utils;

// 判断字符串是否为空
public class ToolUtil {

    public static boolean isEmpty(String str){
        if(str != null && !"".equals(str.trim())){
            return false;
        }
        return true;
    }

}
