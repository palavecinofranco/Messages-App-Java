package com.mycompany.messagesapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/app_messages", "root", "");
        }catch(SQLException e){
            System.out.println(e);
        }
        return connection;
    }
}
