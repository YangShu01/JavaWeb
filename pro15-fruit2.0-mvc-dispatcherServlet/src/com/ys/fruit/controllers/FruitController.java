package com.ys.fruit.controllers;

import com.ys.fruit.dao.FruitDAO;
import com.ys.fruit.dao.impl.FruitDAOImpl;
import com.ys.fruit.pojo.Fruit;
import com.ys.myssm.myspringmvc.ViewBaseServlet;
import com.ys.myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FruitController extends ViewBaseServlet {
    //之前的FruitServlet是一个Servlet组件,那么其中的init方法一定会把被调用
    //之前的init方法内部会出现一句话,super.init()

    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) throws ServletException {
        this.servletContext = servletContext;
        super.init(servletContext);
    }
    private FruitDAO fruitDAO = new FruitDAOImpl();

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //1.设置编码
        request.setCharacterEncoding("UTF-8");

        //2.获取参数
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("fcount");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));

        //4.资源跳转
        response.sendRedirect("fruit.do");
    }

    private void edit(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
    {
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr))
        {
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);

            response.sendRedirect("fruit.do");
        }
    }

    private void add(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");
        String fname = request.getParameter("fname");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Integer fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        Fruit fruit = new Fruit(0,fname,price,fcount,remark);
        fruitDAO.addFruit(fruit);

        response.sendRedirect("fruit.do");
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
    {
        HttpSession session = request.getSession();
        //设置当前页,默认值1
        Integer pageNo = 1;

        String oper = request.getParameter("oper");

        //如果oper!=null说明是通过表单的查询按钮点击的
        //如果oper==null.说明不是表单的按钮点击的
        String keyword =  null;
        if(StringUtil.isNotEmpty(oper)&&"search".equals(oper))
        {
            pageNo = 1;
            keyword = request.getParameter("keyword");

            if(StringUtil.isEmpty(keyword))
            {
                keyword = "";
            }
            session.setAttribute("keyword",keyword);
        }else{
            String pageNoStr = request.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr))
            {
                pageNo = Integer.parseInt(pageNoStr);
            }

            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null)
            {
                keyword = (String) keywordObj;
            }else{
                keyword="";
            }
        }

        session.setAttribute("pageNo",pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword,pageNo);
        session.setAttribute("fruitList",fruitList);

        int fruitCount = fruitDAO.getFruitCount(keyword);

        int pageCount = (fruitCount+5-1)/5;

        session.setAttribute("pageCount",pageCount);

        super.processTemplate("index",request,response);
    }




}
