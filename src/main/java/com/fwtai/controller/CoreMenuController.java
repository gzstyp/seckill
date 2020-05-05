package com.fwtai.controller;

import com.fwtai.service.CoreMenuService;
import com.fwtai.tool.ToolClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/coreMenu")
public final class CoreMenuController{

    @Autowired
    private CoreMenuService coreMenuService;

    @RequestMapping("/queryAllMenu")//coreMenu/queryAllMenu
    @ResponseBody
    public final void queryAllMenu(final HttpServletRequest request,final HttpServletResponse response){
        ToolClient.responseJson(coreMenuService.queryAllMenu(request),response);
    }

    @RequestMapping("/queryMenu")//coreMenu/queryMenu
    @ResponseBody
    public final void queryMenu(final HttpServletRequest request,final HttpServletResponse response){
        ToolClient.responseJson(coreMenuService.queryAllMenu(),response);
    }
}