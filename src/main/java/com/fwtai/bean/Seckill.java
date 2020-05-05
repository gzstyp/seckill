package com.fwtai.bean;

import java.math.BigDecimal;

/**
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-05-05 23:16
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
 */
public class Seckill{

    private Integer id;

    private String sku;

    private Integer num;

    private BigDecimal price;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getSku(){
        return sku;
    }

    public void setSku(String sku){
        this.sku = sku;
    }

    public Integer getNum(){
        return num;
    }

    public void setNum(Integer num){
        this.num = num;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }
}