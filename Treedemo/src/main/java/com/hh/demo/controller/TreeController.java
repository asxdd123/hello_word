package com.hh.demo.controller;

import com.hh.demo.TreeBean.*;
import com.hh.demo.mapper.UserMapper;
import com.hh.demo.service.MenuDTOService;
import com.hh.demo.utils.BuildTree;
import com.hh.demo.utils.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aa")
public class TreeController {

    @Autowired
    private MenuDTOService service;
    @Autowired
    private UserMapper mapper;

    /**
     * 定义方法求n的阶乘(递归)
     * @param n
     * @return
     */
    @RequestMapping("/qq")
    public long methods4(long n) {
        if (n == 1) {
            //递归方法特点一：有至少一个出口条件
            return 1; 
        }
        //递归方法特点二：在方法中调用自己
            return n * methods4(n - 1);
    }


    /**
     * java组织树形结构展示
     * <p>
     *          1.首先从菜单数据中获取所有根节点。
     * 　　　　2.为根节点建立次级子树并拼接上。
     * 　　　　3.递归为子节点建立次级子树并接上，直至为末端节点拼接上空的“树”。
     *
     * @return
     */
    @RequestMapping("/aa")
    public List<MenuDTO> amethods() {
        List<MenuDTO> menuList = service.menuList();
        /*让我们创建树*/
        MenuTree menuTree = new MenuTree(menuList);
        menuList = menuTree.builTree();
        return menuList;
    }

    /**
     * Java8新特性-使用Stream流递归实现遍历树形结构
     * 比如构建菜单，构建树形结构，数据库一般就使用父id来表示，
     * 为了降低数据库的查询压力，我们可以使用Java8中的Stream流一次性把数据查出来，然后通过流式处理
     * @return
     */
    @RequestMapping("/bb")
    public List<Menu> amethods2() {
        //模拟从数据库查询出来
        List<Menu> menus = Arrays.asList(
                new Menu(1, "根节点", 0),
                new Menu(2, "子节点1", 1),
                new Menu(3, "子节点1.1", 2),
                new Menu(4, "子节点1.2", 2),
                new Menu(5, "根节点1.3", 2),
                new Menu(6, "根节点2", 1),
                new Menu(7, "根节点2.1", 6),
                new Menu(8, "根节点2.2", 6),
                new Menu(9, "根节点2.2.1", 7),
                new Menu(10, "根节点2.2.2", 7),
                new Menu(11, "根节点3", 1),
                new Menu(12, "根节点3.1", 11)
        );
        //获取父节点
        List<Menu> list = menus.stream().filter(t -> t.parentId == 0).map(
                (t) -> {
                    t.setChildList(getChildrens(t, menus));
                    return t;
                }
        ).collect(Collectors.toList());
        return list;
    }

    /**
     * 基于java实现树形结构展示
     * @return
     */
    @RequestMapping("/cc")
    public Tree<Test> amethods3() {
        List<Tree<Test>> trees = new ArrayList<Tree<Test>>();
        List<Test> tests = new ArrayList<Test>();
        tests.add(new Test("0", "", "关于本人"));
        tests.add(new Test("1", "0", "技术学习"));
        tests.add(new Test("2", "0", "兴趣"));
        tests.add(new Test("3", "1", "JAVA"));
        tests.add(new Test("4", "1", "oracle"));
        tests.add(new Test("5", "1", "spring"));
        tests.add(new Test("6", "1", "springmvc"));
        tests.add(new Test("7", "1", "fastdfs"));
        tests.add(new Test("8", "1", "linux"));
        tests.add(new Test("9", "2", "骑行"));
        tests.add(new Test("10", "2", "吃喝玩乐"));
        tests.add(new Test("11", "2", "学习"));
        tests.add(new Test("12", "3", "String"));
        tests.add(new Test("13", "4", "sql"));
        tests.add(new Test("14", "5", "ioc"));
        tests.add(new Test("15", "5", "aop"));
        tests.add(new Test("16", "1", "等等"));
        tests.add(new Test("17", "2", "等等"));
        tests.add(new Test("18", "3", "等等"));
        tests.add(new Test("19", "4", "等等"));
        tests.add(new Test("20", "5", "等等"));

        for (Test test : tests) {
            Tree<Test> tree = new Tree<Test>();
            tree.setItemtype_id(test.getId());
            tree.setParent_itemtypeid(test.getPid());
            tree.setItemtype_name(test.getText());
            List<Map<String, Object>> lmp = new ArrayList<Map<String, Object>>();
            Map<String, Object> mp = new HashMap<String, Object>();
            mp.put("COSTDEVICE_NUMBER", "");
            mp.put("PRICE_PER", "");
            mp.put("ORDER_INDEX", "");
            mp.put("ADJUST_DATE", "");
            mp.put("IS_LEAF", "");
            lmp.add(mp);
            tree.setAttributes(lmp);
            trees.add(tree);
        }

        Tree<Test> t = BuildTree.build(trees);
        return t;
    }

    /**
     * springBoot + Mybatis 实现查询树形结构
     * @return
     */
    @RequestMapping("/dd")
    public List<DeptEmtity> amethods4() {
        List<DeptEmtity> tree = mapper.getNodeTree();
        return tree;
    }


    /**
     * 递归查询子节点
     *
     * @param t     根节点
     * @param menus 所有节点
     * @return 根节点信息
     */
    private static List<Menu> getChildrens(Menu t, List<Menu> menus) {
        List<Menu> list = menus.stream().filter(a -> {
            return Objects.equals(a.getParentId(), t.getId());
        }).map(
                (a) -> {
                    a.setChildList(getChildrens(a, menus));
                    return a;
                }
        ).collect(Collectors.toList());
        return list;
    }
}
