package com.hehe.demo3.filter;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2023-02-07 18:32
 */

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * description:http辅助工具类
 *
 * @author RenShiWei
 * Date: 2021/5/7 22:11
 **/
public class HttpHelper {

    /**
     * description:从request获取body的json数据
     *
     * @param request /
     * @return /
     * @author RenShiWei
     * Date: 2021/5/7 22:44
     */
    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        ServletInputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * description:从request获取body的json数据，并格式化成map形式
     *
     * @param request /
     * @return /
     * @author RenShiWei
     * Date: 2021/5/7 22:44
     */
    @SuppressWarnings("all")
    public static Map<String, Object> getBodyMap(ServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        String bodyString = getBodyString(request);
        if (StringUtils.isNoneEmpty(bodyString)) {
            params = JSON.parseObject(bodyString, Map.class);
        }
        return params;
    }

}



