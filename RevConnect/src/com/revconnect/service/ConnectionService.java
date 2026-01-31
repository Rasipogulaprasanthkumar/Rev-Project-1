package com.revconnect.service;

import com.revconnect.DAO.ConnectionDAO;
import com.revconnect.model.Connection1;

import java.util.List;

public class ConnectionService
{
    private ConnectionDAO connectionDAO=new ConnectionDAO();

    public boolean sendRequest(int senderId,int receiverId)
    {
        if(senderId==receiverId)
        {
            System.out.println("❌ You cannot connect with yourself");
            return false;
        }
        return connectionDAO.sendRequest(senderId,receiverId);
    }
    public List<Connection1> incoming(int userId)
    {
        return connectionDAO.getIncomingRequest(userId);
    }
    public boolean accept(int connectionId)
    {
        return connectionDAO.updateStatus(connectionId,"ACCEPTED");
    }
    public boolean reject(int connectionId)
    {
        return connectionDAO.updateStatus(connectionId,"REJECT");
    }

    public List<Connection1> myConnections(int userId)
    {
        return connectionDAO.getMyConnections(userId);
    }

}
