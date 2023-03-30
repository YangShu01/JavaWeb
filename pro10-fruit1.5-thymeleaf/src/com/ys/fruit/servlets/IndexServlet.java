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
public class IndexServlet extends ViewBaseServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList();

        HttpSession session = req.getSession();
        session.setAttribute("fruitList", fruitList);

        super.processTemplate("index", req, resp);
    }
}
