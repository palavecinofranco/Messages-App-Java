package com.mycompany.messagesapp.dao;

import com.mycompany.messagesapp.models.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    public static void createMessage(Message message){
        ConnectionDB dbConnection = new ConnectionDB();
        PreparedStatement ps = null;
        try(Connection connection = dbConnection.getConnection()){
            String query = "INSERT INTO `messages` (`message`, `author_message`) VALUES (?,?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, message.getMessage());
            ps.setString(2, message.getAuthor());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public static List<Message> readMessages(){
        ConnectionDB dbConnection = new ConnectionDB();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Message> list = new ArrayList<>();

        try(Connection connection = dbConnection.getConnection()) {
            String query = "SELECT * FROM messages";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                Message message = new Message(rs.getString("message"), rs.getString("author_message"));
                message.setId(rs.getInt("id"));
                message.setMessageDate(rs.getTimestamp("date_message").toLocalDateTime());
                list.add(message);
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return list;
    }

    public static void deleteMessage(int id){
        ConnectionDB dbConnection = new ConnectionDB();
        PreparedStatement ps = null;
        try(Connection connection = dbConnection.getConnection()){
            try{
                String query = "DELETE FROM messages WHERE id = ?";
                ps = connection.prepareStatement(query);
                ps.setInt(1, id);
                ps.executeUpdate();
            }catch (SQLException ex){
                System.out.println(ex);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public static void updateMessage(Message message){
        ConnectionDB dbConnection = new ConnectionDB();
        PreparedStatement ps = null;
        try(Connection connection = dbConnection.getConnection()){
            String query = "UPDATE messages SET message = ?, author_message = ? WHERE id = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, message.getMessage());
            ps.setString(2, message.getAuthor());
            ps.setInt(3, message.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public static void loadMessage(Message message) {
        if (message.getId()==0){
            createMessage(message);
        }else {
            updateMessage(message);
        }
    }
}
