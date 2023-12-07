package com.java.mapper;

/**
 * @Author 一条不会飞的咸鱼
 * @Date 2023/12/7 19:03
 * @Version 1.0
 */
public interface BasicObjectConvert<S,T> {

    T toTarget(S s);

    @InheritConfiguration(name="toTarget")
    List<T> toTargetList(List<S> sList);

    S toSource(T t);

    @InheritConfiguration(name="toSource")
    List<S> toSourceList(List<T> tlist);

}
