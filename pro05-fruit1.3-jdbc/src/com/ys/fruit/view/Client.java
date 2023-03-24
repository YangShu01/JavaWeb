package com.ys.fruit.view;

import com.ys.fruit.controller.Menu;

public class Client
{
    public static void main(String[] args) {
        Menu menu = new Menu();
        boolean flag = true;
        while (flag)
        {
            int slt = menu.showMainMenu();

            switch(slt)
            {
                case 1:
                    menu.showFruitList();
                    break;
                case 2:
                    menu.addFruit();;
                    break;
                case 3:
                    menu.showFruitInfo();
                    break;
                case 4:
                    menu.delFruit();
                    break;
                case 5:
                    flag = menu.exit();
                    break;
                default:
                    System.out.println("你不按套路出牌");
                    break;
            }
        }

        System.out.println("谢谢使用!再见");
    }
}
