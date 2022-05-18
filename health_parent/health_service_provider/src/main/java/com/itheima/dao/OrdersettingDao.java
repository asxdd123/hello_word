package com.itheima.dao;

import com.itheima.pojo.OrderSetting;
import java.util.Date;
import java.util.List;
import java.util.Map;


public interface OrdersettingDao {
    //更新已预约人数
    public void editReservationsByOrderDate(OrderSetting orderSetting);
    long findCountByOrderDate(Date orderDate);

    //更新可预约人数
    void editNumberByOrderDate(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);

    //根据日期范围查询预约设置信息
    List<OrderSetting> getOrderSettingByMonth(Map map);

    //根据预约日期查询预约设置信息
    public OrderSetting findByOrderDate(Date orderDate);


}
