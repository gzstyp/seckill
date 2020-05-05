package com.fwtai.service;

import com.fwtai.dao.DaoBase;
import com.fwtai.tool.ToolClient;
import com.fwtai.tool.ToolMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2018-06-01 8:58
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
 */
@Service
public class CoreMenuService{

    @Autowired
    private DaoBase daoBase;

    /**
     * 获取List<HashMap
     */
    private final List<HashMap<String,Object>> queryListMap(){
        try{
            return daoBase.queryForListHashMap("sys_code.queryMenu",null);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取数据菜单
     */
    public String queryAllMenu(final HttpServletRequest request){
        try{
            /*查询所有菜单|原始的数据*/
            final List<HashMap<String,Object>> allMenu = queryListMap();
            //根节点
            final List<HashMap<String,Object>> rootMenu = new ArrayList<HashMap<String,Object>>();
            for(final HashMap<String,Object> map : allMenu){
                //父节点是1024的为最外层的根节点。
                if(map.get("pid").toString().equals("1024")){
                    rootMenu.add(map);
                }
            }
            //为根菜单设置子菜单，getClild是递归调用的
            for(final HashMap<String,Object> map : rootMenu){
                /* 获取根节点下的所有子节点 使用getChild方法*/
                final List<HashMap<String,Object>> children = getChild(map.get("kid").toString(),allMenu);
                map.put("children",children);
            }
            return ToolClient.queryJson(rootMenu);
        }catch(Exception e){
            e.printStackTrace();
            return ToolClient.exceptionJson("系统出现异常!");
        }
    }

    /**
     * 获取子节点
     * @param id 父节点id
     * @param allMenu 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    private List<HashMap<String,Object>> getChild(final String id,final List<HashMap<String,Object>> allMenu){
        //子菜单
        final List<HashMap<String,Object>> childList = new ArrayList<HashMap<String,Object>>();
        for(final HashMap<String,Object> map : allMenu){
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较,相等说明：为该根节点的子节点。
            if(map.get("pid").toString().equals(id)){
                childList.add(map);
            }
        }
        //递归
        for(final HashMap<String,Object> nav : childList){
            nav.put("children",getChild(nav.get("kid").toString(),allMenu));
        }
        //Collections.sort(childList,order());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if(childList.size() == 0){
            return new ArrayList<HashMap<String,Object>>();
        }
        return childList;
    }

    /**
     * 获取数据菜单
     */
    public String queryAllMenu(){
        try{
            final List<HashMap<String,Object>> allMenu = queryListMap();
            final List<HashMap<String,Object>> rootMenu = new ToolMenu("kid","pid","children").initMenu(allMenu,"1024");
            return ToolClient.queryJson(rootMenu);
        }catch(Exception e){
            e.printStackTrace();
            return ToolClient.exceptionJson("系统出现异常!");
        }
    }
}