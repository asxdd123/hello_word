package com.hh.demo.demo2;

import java.util.List;

/**
 * @Author: 就不告诉你
 * @CreateTime: 2022-10-24 11:35
 */
public class Student {
    /**
     * id
     */
    public Integer id;
    /**
     * 名称
     */
    public String name;
    /**
     * 父id ，根节点为0
     */
    public Integer parentId;
    /**
     * 子节点信息
     */
    public List<Student> childList;


    public Student(Integer id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public Student(Integer id, String name, Integer parentId, List<Student> childList) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.childList = childList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Student> getChildList() {
        return childList;
    }

    public void setChildList(List<Student> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", childList=" + childList +
                '}';
    }
}
