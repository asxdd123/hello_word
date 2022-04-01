package com.hehe.demo3;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hehe.demo3.bean.Student;
import com.hehe.demo3.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class Demo3ApplicationTests {

    @Autowired
    private StudentMapper mapper;

    @Test
    void contextLoads() {
        int current = 1;
        int size = 5;

        IPage page = new Page(current, size);
        IPage iPage = mapper.selectPage(page, null);
        List records = iPage.getRecords();
        for (Object record : records) {
            System.out.println("record="+record);
        }
        long total = iPage.getTotal();
        System.out.println("total="+total);

        long pages = iPage.getPages();
        System.out.println("pages="+pages);

        long current1 = iPage.getCurrent();
        System.out.println("current1="+current1);

        long size1 = iPage.getSize();
        System.out.println("size1="+size1);
    }

}
