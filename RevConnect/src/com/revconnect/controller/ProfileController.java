package com.revconnect.controller;

import com.revconnect.model.Profile;
import com.revconnect.model.User;
import com.revconnect.service.ProfileService;

import java.util.Scanner;

public class ProfileController
{
    private ProfileService profileService =new ProfileService();
    private Scanner sc=new Scanner(System.in);

    public void profileMenu(User user)
    {
        while(true)
        {
            System.out.println("\n===== PROFILE =====");
            System.out.println("1. Create Profile");
            System.out.println("2. View Profile");
            System.out.println("3. Edit Profile");
            System.out.println("4. Back");
            System.out.print("Choose: ");

            int ch=sc.nextInt();
            sc.nextLine();
            switch(ch)
            {
                case 1:
                    createProfile(user);
                    break;
                case 2:
                    viewProfile(user);
                    break;
                case 3:
                    editProfile(user);
                    break;
                case 4:
                    return;
            }
        }
    }

    public void createProfile(User user)
    {
        Profile p=new Profile();
        p.setUserId(user.getUserId());

        System.out.println("Name: ");
        p.setName(sc.nextLine());

        System.out.println("Bio: ");
        p.setBio(sc.nextLine());

        System.out.println("Location: ");
        p.setLocation(sc.nextLine());

        System.out.println("Website: ");
        p.setWebsite(sc.nextLine());

        System.out.print("Category: ");
        p.setCategory(sc.nextLine());

        System.out.print("Business Address (optional): ");
        p.setBusinessAddress(sc.nextLine());

        if(profileService.createProfile(p))
        {
            System.out.println("Profile created successfully ✅");
        }
        else {
            System.out.println("Failed to create profile ❌");
        }
    }

    public void viewProfile(User user)
    {
        Profile p=profileService.viewProfile(user.getUserId());
        if(p==null) {
            System.out.println("No profile found ❌");
            return;
        }

        System.out.println("\n----- PROFILE -----");
        System.out.println("Name: " + p.getName());
        System.out.println("Bio: " + p.getBio());
        System.out.println("Location: " + p.getLocation());
        System.out.println("Website: " + p.getWebsite());
        System.out.println("Category: " + p.getCategory());
        System.out.println("Address: " + p.getBusinessAddress());

    }
    public void editProfile(User user)
    {
        Profile existing=profileService.viewProfile(user.getUserId());
        if(existing==null)
        {
            System.out.println("No profile found. Please create one first ❌");
            return;
        }
        System.out.println("\n----- EDIT PROFILE -----");
        System.out.println("Press ENTER to keep old value");

        System.out.println("Name ("+existing.getName()+"): ");
        String name=sc.nextLine();
        if (!name.isEmpty()) existing.setName(name);


        System.out.print("Bio (" + existing.getBio() + "): ");
        String bio = sc.nextLine();
        if (!bio.isEmpty()) existing.setBio(bio);

        System.out.print("Location (" + existing.getLocation() + "): ");
        String location = sc.nextLine();
        if (!location.isEmpty()) existing.setLocation(location);

        System.out.print("Website (" + existing.getWebsite() + "): ");
        String website = sc.nextLine();
        if (!website.isEmpty()) existing.setWebsite(website);

        System.out.print("Category (" + existing.getCategory() + "): ");
        String category = sc.nextLine();
        if (!category.isEmpty()) existing.setCategory(category);

        System.out.print("Business Address (" + existing.getBusinessAddress() + "): ");
        String address = sc.nextLine();
        if (!address.isEmpty()) existing.setBusinessAddress(address);

        if(profileService.editProfile(existing))
        {
            System.out.println("Profile updated successfully ✅");
        }
        else
        {
            System.out.println("Profile update failed ❌");
        }

    }

}
