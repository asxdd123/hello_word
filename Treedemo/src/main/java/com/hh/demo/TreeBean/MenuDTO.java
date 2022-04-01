package com.hh.demo.TreeBean;
import lombok.Data;
import java.util.List;

@Data
public class MenuDTO {

    private Long dept_id;
    private Long parent_id;
    private String name;
    private Integer order_num;
    private Integer del_flag;
    //子节点信息
    private List<MenuDTO> children;

    public MenuDTO(Long dept_id, Long parent_id, String name, Integer order_num, Integer del_flag, List<MenuDTO> children) {
        this.dept_id = dept_id;
        this.parent_id = parent_id;
        this.name = name;
        this.order_num = order_num;
        this.del_flag = del_flag;
        this.children = children;
    }

    public MenuDTO(Long dept_id, Long parent_id, String name, Integer order_num, Integer del_flag) {
        this.dept_id = dept_id;
        this.parent_id = parent_id;
        this.name = name;
        this.order_num = order_num;
        this.del_flag = del_flag;
    }
}
