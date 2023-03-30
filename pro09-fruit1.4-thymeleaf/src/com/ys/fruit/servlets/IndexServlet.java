package com.ys.fruit.servlets;

import com.ys.fruit.dao.FruitDAO;
import com.ys.fruit.dao.impl.FruitDAOImpl;
import com.ys.fruit.pojo.Fruit;
import com.ys.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList();
        //保存到session作用域
        HttpSession session = req.getSession();
        session.setAttribute("fruitList",fruitList);
        //此处视图名称:index
        //那么thymeleaf会将这个逻辑视图对应到物理视图上去
        //逻辑视图名称:index
        //物理视图名称:view-prefix+逻辑视图名称+view-suffix
        //所以真实视图名称:     /      index    .html
        super.processTemplate("index",req,resp);
    }
}
