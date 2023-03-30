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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Integer pageNo = 1;
        String oper = req.getParameter("oper");
        String keyword = null;
        if(StringUtil.isNotEmpty(oper)&&"search".equals(oper))
        {
            pageNo = 1;
            keyword = req.getParameter("keyword");
            if(StringUtil.isEmpty(keyword))
            {
                keyword = "";
            }
            session.setAttribute("keyword",keyword);
        }else{
            String pageNoStr = req.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr))
            {
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null)
            {
                keyword = (String) keywordObj;
            }else{
                keyword = "";
            }
        }

        session.setAttribute("pageNo",pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword,pageNo);

        session.setAttribute("fruitList", fruitList);

        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);

        //总页数
        int pageCount = (fruitCount+5-1)/5;
        session.setAttribute("pageCount", pageCount);
        super.processTemplate("index", req, resp);
    }
}
