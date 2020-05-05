package com.fwtai.service;

import com.fwtai.dao.DaoBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 秒杀，数据库实现,请在test包查看
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-05-05 23:14
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
@Service
public class SeckillService{

    @Autowired
    private DaoBase daoBase;

    public boolean seckill(final String sku,final Integer buys){
        final HashMap<String,Object> params = new HashMap<>();
        params.put("sku",sku);
        params.put("buys",buys);
        return daoBase.execute("sys_code.seckill",params) > 0;
    }

    public Integer getStock(){
        return daoBase.queryForInteger("sys_code.getStock");
    }
}