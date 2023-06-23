package com.hehe.utils;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Filter
 **/
public class Filter implements Serializable {


    /**
     * 需要过滤的属性
     */
    private String fieldName;
    /**
     * 过滤条件 比较符
     * 运算符使用说明
     * 运算符	        Sql	                                noSql	            备注	        是否支持
     * =	        name=’王店变’	                name=王店变		                            支持
     * !=与<>	    name!=’王店变’	                Name!=王店变	      需要结合其它索引使用	支持，需结合其它索引使用
     * <	        Id > ‘1000000’	                Id > ‘1000000’		                        支持
     * >	        Id <‘1000000’	                Id < ‘1000000’		                        支持
     * like	    name like ‘王店%’	            name=王店	          全文检索接口	        不支持
     * In	        name in (‘王店变’,’富阳变’)	    name=王店变,富阳变		                    支持
     * Not in	    name not in (‘王店变’,’富阳变’)	Name!=王店变,富阳变	  需要结合其它索引使用	不支持
     */
    private String compare;
    /**
     * 比较值
     */
    private String fieldValue;

    public static Set<String> compareSet(){
        Set<String> compareSet = new HashSet<>();
        compareSet.add("=");
        compareSet.add("!=");
        compareSet.add("<>");
        compareSet.add("<");
        compareSet.add("<=");
        compareSet.add(">");
        compareSet.add(">=");
        compareSet.add("like");
        compareSet.add("in");
        compareSet.add("not in");

        return compareSet;

    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
