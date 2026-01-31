package com.revconnect.controller;

import com.revconnect.model.User;
import com.revconnect.service.AuthService;

import java.util.Scanner;



public class AuthController
{
    private Scanner sc=new Scanner(System.in);
    private AuthService authService=new AuthService();

    public User start()
    {
        while(true)
        {
            System.out.println("\n===== REVCONNECT =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int ch=sc.nextInt();
            sc.nextLine();
            switch (ch)
            {
                case 1:
                    register();
                    break;
                case 2:
                    return login();
                case 3:
                    System.out.println("Goodbye 👋");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice ❌");
            }
        }
    }

    private void register()
    {
        System.out.print("Username : ");
        String username=sc.nextLine();

        System.out.print("Email : ");
        String email=sc.nextLine();

        System.out.print("Password : ");
        String password=sc.nextLine();

        System.out.println("Role (PERSONAL/CREATOR/BUSINESS) : ");
        String role=sc.nextLine().toUpperCase();

        User user=new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        if(authService.Register(user))
        {
            System.out.println("Registered successfully ✅");
        }
        else {
            System.out.println("Registration failed ❌");
        }
    }
    private User login()
    {
        System.out.println("Enter Your Email : ");
        String email=sc.nextLine();

        System.out.println("Enter Your Password : ");
        String password=sc.nextLine();

        User user=authService.login(email,password);

        if(user!=null)
        {
            System.out.println("Login successfull ✅ Welcome "+user.getUsername());
            return user;
        }
        return null;
    }

}
