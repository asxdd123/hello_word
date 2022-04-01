package com.hh.demo.TreeBean;

/**
 * 数据库表实体类
 */
public class Test {
    private String id;
    private String pid;
    private String text;

    private String costdevice_number;
    private String price_per;
    private String order_index;
    private String adjust_date;
    private String is_leaf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Test(String id, String pid, String text) {
        super();
        this.id = id;
        this.pid = pid;
        this.text = text;
    }
    public String getCostdevice_number() {
        return costdevice_number;
    }

    public void setCostdevice_number(String costdevice_number) {
        this.costdevice_number = costdevice_number;
    }

    public String getPrice_per() {
        return price_per;
    }

    public void setPrice_per(String price_per) {
        this.price_per = price_per;
    }

    public String getOrder_index() {
        return order_index;
    }

    public void setOrder_index(String order_index) {
        this.order_index = order_index;
    }

    public String getAdjust_date() {
        return adjust_date;
    }

    public void setAdjust_date(String adjust_date) {
        this.adjust_date = adjust_date;
    }

    public String getIs_leaf() {
        return is_leaf;
    }

    public void setIs_leaf(String is_leaf) {
        this.is_leaf = is_leaf;
    }

    public Test() {
        super();
    }

    @Override
    public String toString() {
        return "Test [id=" + id + ", pid=" + pid + ", text=" + text + "]";
    }

}
