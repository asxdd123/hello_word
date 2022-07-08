package com.hehe.springbootjdbc.mapper;

import com.github.pagehelper.util.StringUtil;
import com.hehe.springbootjdbc.pojo.Student;
import org.springframework.stereotype.Component;

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
public class StudentMapper {

//    @Autowired
//    JdbcTemplate jdbcTemplate;     springboot中封装了JDBC ,只需要调用JdbcTemplate即可

    public List<Student> findNameandPage(Map<String, Object> param) {
        int page = (int) param.get("page");
        int size = (int) param.get("size");
        String name = (String) param.get("name");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "";

        ArrayList<Student> list = new ArrayList<>();
        try {
            //读取配置文件
            InputStream stream = StudentMapper.class.getClassLoader().getResourceAsStream("application.yml");  
            Properties properties = new Properties();
            properties.load(stream);

            //获取参数
            String driverClass = properties.getProperty("driver-class-name");
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            
            //注册数据库类型
            Class.forName(driverClass);
            // 获取数据库连接
            connection = DriverManager.getConnection(url, username, password);
            //创建请求对象(执行者对象)
            statement = connection.createStatement();
            if (StringUtil.isNotEmpty(name)) {
                sql = "select * from student where sname like '%" + name + "%'" + "limit " + (page - 1) + "," + size;
            } else {
                sql = "select * from student " + "limit " + (page - 1) + "," + size;
            }
            //执行sql
            resultSet = statement.executeQuery(sql);
            // 循环结果进行遍历赋值
            while (resultSet.next()) {
                Student student = new Student();
                student.setSid(resultSet.getString("sid"));
                student.setSname(resultSet.getString("sname"));
                student.setSage(resultSet.getString("sage"));
                student.setSsex(resultSet.getString("ssex"));
                student.setUrl(resultSet.getString("url"));

                list.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭
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
        return list;
    }
}
