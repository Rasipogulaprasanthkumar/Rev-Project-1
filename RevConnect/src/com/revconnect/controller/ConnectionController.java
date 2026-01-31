package com.revconnect.controller;

import com.revconnect.model.Connection1;
import com.revconnect.model.User;
import com.revconnect.service.ConnectionService;

import java.util.List;
import java.util.Scanner;

public class ConnectionController
{
    private ConnectionService service=new ConnectionService();
    private Scanner sc =new Scanner(System.in);
    public void connectionMenu(User user)
    {

        while (true)
        {
            System.out.println("\n===== CONNECTIONS =====");
            System.out.println("1. Send request");
            System.out.println("2. View incoming requests");
            System.out.println("3. Accept / Reject request");
            System.out.println("4. My connections");
            System.out.println("5. Back");
            System.out.print("Choose: ");

            int ch=sc.nextInt();
            sc.nextLine();
            switch (ch)
            {
                case 1:
                    send(user);
                    break;
                case 2:
                    incoming(user);
                    break;
                case 3:
                    respond();
                    break;
                case  4:
                    myConnections(user);
                case 5:
                    return;

            }
        }
    }
    private void send(User user)
    {
        System.out.println("Enter user ID to connect :");
        int id=sc.nextInt();
        sc.nextLine();
        if(service.sendRequest(user.getUserId(),id))
        {
            System.out.println("Request sent ✅");
        }
        else
        {
            System.out.println("Request failed ❌");
        }

    }
    private void incoming(User user)
    {
        List<Connection1> list=service.incoming(user.getUserId());

        if(list.isEmpty())
        {
            System.out.println("No Pending requests .....");
            return;
        }
        else {
            for(Connection1 c:list)
            {
                System.out.println("Request ID : "+c.getConnectionId()+" from USER ID : "+c.getSenderId() );
            }
        }
    }
    private void respond()
    {
        System.out.print("Enter request ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("1. Accept  2. Reject: ");
        int ch = sc.nextInt();
        sc.nextLine();

        if(ch==1)
            service.accept(id);
        else service.reject(id);

        System.out.println("Updated successfully ✅");
    }
    private void myConnections(User user)
    {
        List<Connection1> list=service.myConnections(user.getUserId());
        if(list.isEmpty())
        {
            System.out.println("You have no connections yet.");
            return;
        }

        for(Connection1 c:list)
        {
            int other=(c.getSenderId()==user.getUserId())? c.getReceiverId():c.getSenderId();
            System.out.println("Connected with User ID: " + other);
        }
    }
}
