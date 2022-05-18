package com.example.excptionto.until;

import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.*;

public class StringBusinessUtil {
    /**
     * 判断对象或数组中每一个对象是否为空
     * 判断对象是否为null
     * 判断字符串长度是否为0,集合类是否为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {

        if (obj == null) {
            return true;
        }

        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0 || "null".equalsIgnoreCase(obj.toString());
        }

        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if (obj instanceof Object[]) {

            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }

            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    /**
     * 字符串转驼峰
     *
     * @param str
     * @return
     */
    public static String strToCamel(String str) {

        StringBuffer buffer = new StringBuffer();
        if (str != null && str.length() > 0) {
            if (str.contains("_")) {
                String[] chars = str.split("_");
                int size = chars.length;
                if (size > 0) {
                    List<String> list = Lists.newArrayList();
                    for (String s : chars) {
                        if (s != null && s.trim().length() > 0) {
                            list.add(s);
                        }
                    }
                    size = list.size();
                    if (size > 0) {
                        buffer.append(list.get(0));
                        for (int i = 1; i < size; i++) {
                            String s = list.get(i);
                            buffer.append(s.substring(0, 1).toUpperCase());
                            if (s.length() > 1) {
                                buffer.append(s.substring(1));
                            }
                        }
                    }
                }
            } else {
                buffer.append(str);
            }
        }
        return buffer.toString();
    }

    /**
     * String类型字符串转换为集合
     *
     * @param strArr
     * @return
     */
    public static List<Long> strToLongList(String strArr) {
        List<Long> idList = new ArrayList<Long>();
        String[] d = strArr.split(",");
        for (int i = 0, size = d.length; i < size; i++) {
            if (d[i] != null) {
                idList.add(Long.parseLong(d[i]));
            }
        }
        return idList;
    }

    /**
     * 装换为integer
     *
     * @param str
     * @return
     */
    public static Integer parseInteger(String str) {
        if (!isNullOrEmpty(str)) {
            return Integer.parseInt(str);
        } else {
            return null;
        }
    }

    /**
     * 将数据库字段转换成属性
     */
    public static String columnToProperty(String column) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (isNullOrEmpty(column)) {
            // 没必要转换
            return "";
        } else if (!column.contains("_")) {
            // 不做转换
            return column;
        } else {
            // 用下划线将原始字符串分割
            String[] columns = column.split("_");
            for (String columnSplit : columns) {
                // 跳过原始字符串中开头、结尾的下换线或双重下划线
                if (isNullOrEmpty(columnSplit)) {
                    continue;
                }
                // 处理真正的驼峰片段
                if (result.length() == 0) {
                    // 第一个驼峰片段，全部字母都小写
                    result.append(columnSplit.toLowerCase());
                } else {
                    // 其他的驼峰片段，首字母大写
                    result.append(columnSplit.substring(0, 1).toUpperCase()).append(columnSplit.substring(1).toLowerCase());
                }
            }
            return result.toString();
        }

    }

    /**
     * 驼峰转换下划线
     */
    public static String propertyToColumn(String property) {
        if (isNullOrEmpty(property)) {
            return "";
        }

        if (property.contains("_")) {
            return property;
        }

        StringBuilder column = new StringBuilder();
        column.append(property.substring(0, 1).toLowerCase());
        for (int i = 1; i < property.length(); i++) {
            String s = property.substring(i, i + 1);
            // 在小写字母前添加下划线
            if ((!Character.isDigit(s.charAt(0)) && Objects.equals(s, s.toUpperCase())
                    && !Objects.equals(",", s.toUpperCase()))) {
                column.append("_");
            }
            // 其他字符直接转成小写
            column.append(s.toLowerCase());
        }

        return column.toString();
    }

    public static <T> T mapToBean(Map<String, Object> map, Class<T> c) {
        try {
            T t = c.newInstance();
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                Field f = c.getDeclaredField(entry.getKey());
                f.setAccessible(true);
                f.set(t, entry.getValue());
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("转换失败！");
        }
    }

}
