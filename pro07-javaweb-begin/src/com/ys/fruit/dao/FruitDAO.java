package com.ys.fruit.dao;

import com.ys.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    List<Fruit> getFruitList();

    boolean addFruit(Fruit fruit);
    boolean updateFruit(Fruit fruit);
    Fruit getFruitByFname(String fname);
    boolean delFruit(String fname);

}
