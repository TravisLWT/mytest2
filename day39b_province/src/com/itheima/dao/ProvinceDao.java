package com.itheima.dao;

import com.itheima.bean.Province;
import com.itheima.utils.C3P0Utils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Description:
 * @Author: yp
 */
public class ProvinceDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(C3P0Utils.getDataSource());

    public List<Province> findAll() throws Exception {
        String sql = "select * from province";
        List<Province> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Province.class));
        return list;
    }
}
