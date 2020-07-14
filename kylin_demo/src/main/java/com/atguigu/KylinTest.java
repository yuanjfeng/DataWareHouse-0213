package com.atguigu;

import java.sql.*;

public class KylinTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Kylin_JDBC 驱动
        String KYLIN_DRIVER = "org.apache.kylin.jdbc.Driver";

        //Kylin_URL
        String KYLIN_URL = "jdbc:kylin://hadoop102:7070/sencond_project";

        //Kylin的用户名
        String KYLIN_USER = "ADMIN";

        //Kylin的密码
        String KYLIN_PASSWD = "KYLIN";
        Class.forName(KYLIN_DRIVER);
        Connection connection = DriverManager.getConnection(KYLIN_URL, KYLIN_USER, KYLIN_PASSWD);
        PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from dwd_fact_order_detail group by province_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("" + resultSet.getString(1) + resultSet.getLong(2));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }

}
