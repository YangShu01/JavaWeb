package com.ys.servlets;

import com.ys.fruit.dao.FruitDAO;
import com.ys.fruit.dao.impl.FruitDAOImpl;
import com.ys.fruit.pojo.Fruit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = req.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");

        FruitDAO fruitDAO = new FruitDAOImpl();

        boolean flag = fruitDAO.addFruit(new Fruit(0,fname,price,fcount,remark));
        System.out.println(flag ? "添加成功":"添加失败");
    }
}
