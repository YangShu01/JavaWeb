package com.ys.fruit.dao.impl;

import com.ys.fruit.dao.FruitDAO;
import com.ys.fruit.pojo.Fruit;
import com.ys.myssm.basedao.BaseDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO
{
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }



}