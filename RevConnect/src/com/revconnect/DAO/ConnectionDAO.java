package com.revconnect.DAO;

import com.revconnect.model.Connection1;
import com.revconnect.util.DBConnection;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDAO
{
    public boolean sendRequest(int senderId,int receiverId)
    {
        try(Connection con= DBConnection.getConnection();
            PreparedStatement pst= con.prepareStatement("insert into connections (sender_id,receiver_id) values (?,?);"))
        {
            pst.setInt(1,senderId);
            pst.setInt(2,receiverId);
            return pst.executeUpdate()>0;

        }
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Request already exists ❌");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //Incoming Request
    public List<Connection1> getIncomingRequest(int userId) {
        List<Connection1> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement("select * from connections where receiver_id=? AND status='PENDING'")) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Connection1 c = new Connection1();
                c.setConnectionId(rs.getInt("connection_id"));
                c.setSenderId(rs.getInt("sender_id"));
                c.setReceiverId(rs.getInt("receiver_id"));
                c.setStatus(rs.getString("status"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //Accept/Reject
    public boolean updateStatus(int connectionId, String status)
    {
        try(Connection con=DBConnection.getConnection();
        PreparedStatement pst=con.prepareStatement("Update connections SET status=? where connection_id=?"))
        {
            pst.setString(1,status);
            pst.setInt(2,connectionId);
            return pst.executeUpdate()>0;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public List<Connection1> getMyConnections(int userId)
    {
        List<Connection1> list=new ArrayList<>();
        try(Connection con=DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("select * from connections where status='ACCEPTED' AND (sender_id=? or receiver_id=?) "))
        {
            pst.setInt(1,userId);
            pst.setInt(2,userId);
            ResultSet rs= pst.executeQuery();

            while (rs.next()) {
                Connection1 c = new Connection1();
                c.setConnectionId(rs.getInt("connection_id"));
                c.setSenderId(rs.getInt("sender_id"));
                c.setReceiverId(rs.getInt("receiver_id"));
                c.setStatus(rs.getString("status"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(c);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
