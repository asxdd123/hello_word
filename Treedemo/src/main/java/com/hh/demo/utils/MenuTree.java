package com.hh.demo.utils;

import com.hh.demo.TreeBean.MenuDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree树结构工具类
 */
public class MenuTree {
    private List<MenuDTO> menuList = new ArrayList<MenuDTO>();

    public MenuTree(List<MenuDTO> menuList) {
        this.menuList = menuList;
    }

    //建立树形结构
    public List<MenuDTO> builTree() {
        List<MenuDTO> treeMenus = new ArrayList<MenuDTO>();
        for (MenuDTO menuNode : getRootNode()) {
            menuNode = buildChilTree(menuNode);
            treeMenus.add(menuNode);
        }

        return treeMenus;
    }

    //递归，建立子树形结构
    private MenuDTO buildChilTree(MenuDTO pNode) {
        List<MenuDTO> chilMenus = new ArrayList<MenuDTO>();
        for (MenuDTO menuNode : menuList) {
            if (menuNode.getParent_id().equals(pNode.getDept_id())) {
                chilMenus.add(buildChilTree(menuNode));
            }
        }
        pNode.setChildren(chilMenus);
        return pNode;
    }

    //获取根节点
    private List<MenuDTO> getRootNode() {
        List<MenuDTO> rootMenuLists = new ArrayList<MenuDTO>();
        for (MenuDTO menuNode : menuList) {
            if (0L == (menuNode.getParent_id())) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }
}
