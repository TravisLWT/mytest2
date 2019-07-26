package com.itheima.web;

import com.itheima.service.ProvinceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: yp
 */
@WebServlet("/province")
public class ProvinceServlet extends HttpServlet {

    private ProvinceService provinceService  = new  ProvinceService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //0 处理乱码
            response.setContentType("text/html;charset=utf-8");

            //1.调用业务 获得省份的数据(json数据)
            String jsonData  =  provinceService.findAll();
            //2.把省份的数据(json数据)响应给客户端
            response.getWriter().print(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
           /* //方式1: 从Mysql获得返回
            try {
                String data = provinceService.getFromMySql();
                response.getWriter().print(data);
            } catch (Exception e1) {
                e1.printStackTrace();
            }*/
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
