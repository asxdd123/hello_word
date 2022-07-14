package com.hehe.springbootjdbc.mapper;

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
 * @CreateTime: 2022-07-10 17:55
 */
@Component
public class StudentMapper {

    public List<Student> findNameandPage(Map<String, Object> param) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            InputStream stream = StudentMapper.class.getClassLoader().getResourceAsStream("application.yml");
            Properties pro = new Properties();
            pro.load(stream);

            String driver = pro.getProperty("driver-class-name");
            String url = pro.getProperty("url");
            String username = pro.getProperty("username");
            String password = pro.getProperty("password");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String sql = "select * from student";
            resultSet = statement.executeQuery(sql);
            ArrayList<Student> list = new ArrayList<>();
            while (resultSet.next()) {
                Student stu = new Student();
                stu.setUrl(resultSet.getString("url"));
                stu.setSid(resultSet.getString("sid"));
                stu.setSname(resultSet.getString("sname"));
                stu.setSsex(resultSet.getString("ssex"));

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
