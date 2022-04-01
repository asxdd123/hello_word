package com.hehe.demo3.dto;


import com.hehe.demo3.utils.QueryPageBean;
import lombok.Data;

/**
 * 封装前端对象
 */
@Data
public class StudentDto extends QueryPageBean {

    private String Sid;

    private String Sname;

    private String Sage;

    private String Ssex;
}
