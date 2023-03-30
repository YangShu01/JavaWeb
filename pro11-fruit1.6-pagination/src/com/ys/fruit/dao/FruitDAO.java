package com.ys.fruit.dao;

import com.ys.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    List<Fruit> getFruitList(Integer pageNo);
   Fruit getFruitById(Integer fid);
   void updateFruit(Fruit  fruit);;
   void delFruit(Integer fid);
   void addFruit(Fruit fruit);

   int getFruitCount();

}
