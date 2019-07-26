package com.itheima.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.bean.Province;
import com.itheima.dao.ProvinceDao;

import java.util.List;

/**
 * @Description:
 * @Author: yp
 */
public class ProvinceServiceOri {
    private ProvinceDao provinceDao = new ProvinceDao();

    
    public String findAll() throws Exception {
        //1.调用Dao 获得List
        List<Province> list =   provinceDao.findAll();
        //2.把list转成json 返回
        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper.writeValueAsString(list);
        return data;
    }
}
