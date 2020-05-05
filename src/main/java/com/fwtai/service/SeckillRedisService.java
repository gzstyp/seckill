package com.fwtai.service;

import com.fwtai.tool.JedisPoolUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 秒杀，redis实现
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2020-05-05 23:14
 * @QQ号码 444141300
 * @Email service@dwlai.com
 * @官网 http://www.fwtai.com
*/
@Service
public class SeckillRedisService{

    public boolean set(final String sku,final Integer buys){
        final JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set(sku,String.valueOf(buys));
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    //剩余数量
    public String get(final String sku){
        final JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.get(sku);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public boolean seckill(final String sku,final Integer buys){
        final JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            int num = Integer.parseInt(jedis.get("1024"));
            if(num < buys){
                return false;
            }
            final Long aLong = jedis.decrBy(sku,buys);
            return aLong >= 0;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }
}