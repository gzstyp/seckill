package com.fwtai.boot;

import com.fwtai.service.SeckillRedisService;
import com.fwtai.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-05-05 23:45
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillRedis{

    //购买数量
    private final AtomicInteger goodsSale = new AtomicInteger();

    //统计
    private final AtomicInteger acountNum = new AtomicInteger();

    private final String sku = "1024";

    private final Integer buys = 3;

    @Autowired
    private SeckillRedisService seckillService;

    @Test
    public void contextLoads(){
    }

    // redis实现
    @Test
    public void sekill() throws InterruptedException{
        for(int i = 0; i < 3000; i++){
            final Thread thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    boolean rows = seckillService.seckill(sku,buys);
                    if(rows){
                        acountNum.incrementAndGet();//自增长
                        goodsSale.addAndGet(buys);
                        final String s = seckillService.get(sku);
                        seckillService.set(sku,Integer.parseInt(s));
                    }
                }
            });
            thread.start();
            thread.join();
        }
        System.out.println("===========成功购买的顾客数量" + acountNum);
        System.out.println("===========成功购买的商品数量" + goodsSale);
        System.out.println("============ 商品库存剩余数量" + seckillService.get(sku));
    }
}