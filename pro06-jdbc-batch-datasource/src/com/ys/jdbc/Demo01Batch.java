package com.ys.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo01Batch {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true", "root", "zxys10177");
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        for(int i = 0;i<10;i++)
        {
            psmt.setString(1,"榴莲"+i);
            psmt.setInt(2,15);
            psmt.setInt(3,100);
            psmt.setString(4,"榴莲是一种神奇的水果");

            psmt.addBatch();
            if(i%1000==0)
            {
                psmt.executeBatch();
                psmt.clearBatch();
            }

        }

        int[] count = psmt.executeBatch();

        for(int i = 0;i<count.length;i++)
        {
            System.out.println(count[i]);
        }

        psmt.close();
        conn.close();
    }
}
