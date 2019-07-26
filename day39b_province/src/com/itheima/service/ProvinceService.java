package com.itheima.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.bean.Province;
import com.itheima.dao.ProvinceDao;
import com.itheima.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Description:
 * @Author: yp
 */
public class ProvinceService {
    private ProvinceDao provinceDao = new ProvinceDao();


    public String findAll() throws Exception {
        String data = null;
        Jedis jedis = null;
        try {
            //1.先从Redis获得
            jedis = JedisUtils.getJedis();

            data = getFromRedis(jedis);
            if (data == null) {
                //2.没有,
                System.out.println("Redis没有数据, 从Mysql获得,再存到Redis...");
                // 从MySQL获得
                data = getFromMySql();
                // 再存到Redis
                saveToRedis(jedis, data);
            } else {
                System.out.println("Redis有数据, 直接从Redis里面获得...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //方式二
            System.out.println("Redis服务器挂了, 从MySql获得...");
            data = getFromMySql();
        } finally {
            JedisUtils.close(jedis);
        }



        return data;
    }

    //把数据保存到Redis
    private void saveToRedis(Jedis jedis, String data) {
        if(jedis != null){
            jedis.set("province_data",data);
        }
    }

    //从Redis获得
    private String getFromRedis(Jedis jedis) {
        if (jedis != null) {
            return jedis.get("province_data");
        }
        return null;
    }

    //从MySql获得  Ctrl+Alt+M 抽取方法
    public String getFromMySql() throws Exception {
        //1.调用Dao 获得List
        List<Province> list = provinceDao.findAll();
        //2.把list转成json 返回
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(list);
    }
}
