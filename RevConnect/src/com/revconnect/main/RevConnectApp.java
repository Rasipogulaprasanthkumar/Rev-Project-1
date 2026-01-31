package com.revconnect.main;
import com.revconnect.controller.*;
import com.revconnect.model.User;
import com.revconnect.service.ReviewService;
import com.revconnect.util.*;

import java.sql.Connection;
import java.util.Scanner;

public class RevConnectApp
{
    public static void main(String[] args)
    {
      AuthController authController=new AuthController();
        User loggedInUser=authController.start();
        if(loggedInUser!=null)
        {
            System.out.println("\nUser session started for: "+loggedInUser.getUsername()+"("+
                    loggedInUser.getRole()+")");
        }

        ProfileController profileController=new ProfileController();
        ReviewController reviewController=new ReviewController();
        ConnectionController connectionController = new ConnectionController();
        PostController postController = new PostController();


        if(loggedInUser!=null)
        {
            while(true)
            {
                System.out.println("\n===== DASHBOARD =====");
                System.out.println("1. Profile");
                System.out.println("2. View Reviews");
                System.out.println("3. Connections");
                System.out.println("4. Posts");
                System.out.println("5. Logout");

                System.out.print("Choose: ");

                Scanner sc=new Scanner(System.in);
                int ch=sc.nextInt();
                switch (ch)
                {
                    case 1:
                        profileController.profileMenu(loggedInUser);
                        break;
                    case 2:
                        reviewController.reviewMenu(loggedInUser);
                        break;
                    case 3:
                        connectionController.connectionMenu(loggedInUser);
                        break;
                    case 4:
                        postController.postMenu(loggedInUser);
                        break;
                    case 5:
                        System.out.println("Logged out 👋");
                        return;
                }
            }
        }



    }

}
