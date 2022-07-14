package com.hehe.springbootjdbc.mapper;

import com.github.pagehelper.util.StringUtil;
import com.hehe.springbootjdbc.pojo.Student;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-07-07 23:53
 */
@Component
public class StudentMapper3 {

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    static {
        try {
            //读取配置文件
            InputStream stream = StudentMapper3.class.getClassLoader().getResourceAsStream("application.yml");
            Properties prop = new Properties();
            prop.load(stream);

            //获取参数
            String driver = prop.getProperty("driver-class-name");
            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");

            //注册驱动
            Class.forName(driver);

            //连接数据库
            connection = DriverManager.getConnection(url, username, password);
            //创建sql执行者对象
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Student> findNameandPage(Map<String, Object> param) {
        try {
            String sql = "select * from student";
            //执行sql并返回结果
            resultSet = statement.executeQuery(sql);

            ArrayList<Student> list = new ArrayList<>();
            while (resultSet.next()) {
                Student stu = new Student();
                stu.setSid(resultSet.getString("sid"));
                stu.setSname(resultSet.getString("sname"));
                stu.setSsex(resultSet.getString("ssex"));
                stu.setSage(resultSet.getString("sage"));
                stu.setUrl(resultSet.getString("url"));

                list.add(stu);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;

    }


}
