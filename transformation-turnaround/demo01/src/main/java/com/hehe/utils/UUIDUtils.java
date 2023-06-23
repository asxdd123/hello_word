package com.hehe.utils;

import java.util.UUID;

/**
 * @Author Hpl
 * @Description 生成id工具类型
 * @Date 2021/11/16 10:03
 * @Version 1.0
 */
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
