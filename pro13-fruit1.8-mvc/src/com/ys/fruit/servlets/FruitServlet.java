package com.ys.fruit.servlets;

import com.ys.fruit.dao.FruitDAO;
import com.ys.fruit.dao.impl.FruitDAOImpl;
import com.ys.fruit.pojo.Fruit;
import com.ys.myssm.myspringmvc.ViewBaseServlet;
import com.ys.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void update(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.设置参数
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));

        //4.资源跳转
        response.sendRedirect("fruit.do");
    }

    private void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr))
        {
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitById(fid);
            request.setAttribute("fruit",fruit);
            super.processTemplate("edit",request,response);
        }
    }

}
