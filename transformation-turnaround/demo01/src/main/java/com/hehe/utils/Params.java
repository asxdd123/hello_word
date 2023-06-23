package com.hehe.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName RequestParmas
 **/
public class Params<T> implements Serializable {

    /**
     * 指定的返回字段
     */
    private String fields;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页最大记录数
     */
    private Integer pageSize;

    private Integer startIndex;

    /**
     * 查询条件
     */
    private List<Filter> filters;

    private T filter;

    private String[] arr;

    public String[] getArr() {
        return arr;
    }

    public void setArr(String[] arr) {
        this.arr = arr;
    }

    public T getFilter() {
        return filter;
    }

    public void setFilter(T filter) {
        this.filter = filter;
    }

    private Integer pageIndex = 1; //页码:和pageNum一样,前端框架封装的参数为pageIndex

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = (pageIndex == null || pageIndex <= 0) ? 1 : pageIndex;
        this.pageNum = this.pageIndex; // 前端框架封装的参数为pageIndex,默认转换下
    }

    public String getStaDate() {
        return staDate;
    }

    public void setStaDate(String staDate) {
        this.staDate = staDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 需要返回的物理模型集合
     */
    @ApiModelProperty(value = "需要返回的物理模型集合")
    private List<Model> models;

    /**
     * 排序动作
     */
    @ApiModelProperty(value = "排序动作")
    private String orderBy;

    @ApiModelProperty(value = "开始时间")
    private String staDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer page) {
        this.pageNum = (page == null || page <= 0) ? 1 : page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer perPage) {
        this.pageSize = perPage == null || perPage <= 0 ? 20 : perPage;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {

        this.orderBy = orderBy;
    }

    public Integer getStartIndex() {
        return (pageNum - 1) * this.pageSize;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }


    public Page getPage(){
        return new Page(pageNum,pageSize);
    }

}