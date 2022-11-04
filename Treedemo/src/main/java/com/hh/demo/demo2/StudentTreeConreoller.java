package com.hh.demo.demo2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-10-24 13:03
 */
@RestController
@RequestMapping("/tree")
public class StudentTreeConreoller {

    @RequestMapping("/tree")
    public List<Student> queryStudenttree() {
        List<Student> menus = Arrays.asList(
                new Student(1, "根节点0", 0),
                new Student(2, "根节点1", 1),
                new Student(3, "子节点1.1", 2),
                new Student(4, "子节点1.2", 2),
                new Student(5, "子节点1.3", 2),
                new Student(6, "根节点2", 1),
                new Student(7, "子节点2.1", 6),
                new Student(8, "子节点2.2", 6),
                new Student(9, "子节点2.2.1", 7),
                new Student(10, "子节点2.2.2", 7),
                new Student(11, "根节点3", 1),
                new Student(12, "子节点3.1", 11)

        );

        return this.queryTree(menus);

    }

    private List<Student> queryTree(List<Student> menus) {
        List<Student> list = menus.stream().filter(a -> a.getParentId() == 0).collect(Collectors.toList());
        ArrayList<Student> students = new ArrayList<>();
        for (Student student : list) {
            student = this.getChildren(student, menus);
            students.add(student);
        }
        return students;
    }

    private Student getChildren(Student student, List<Student> menus) {
        ArrayList<Student> list = new ArrayList<>();
        for (Student menu : menus) {
            if(menu.getParentId() == student.getId()){
               list.add(getChildren(menu,menus));
            }
        }
        student.setChildList(list);
        return student;
    }


}

