package com.revconnect.controller;

import com.revconnect.DAO.ReviewDAO;
import com.revconnect.model.Review;
import com.revconnect.model.User;
import com.revconnect.service.ReviewService;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;
import java.util.Scanner;

public class ReviewController
{
    private ReviewService reviewService=new ReviewService();
    private Scanner sc=new Scanner(System.in);

    public void reviewMenu(User loggedInUser)
    {
        while(true)
        {
            System.out.println("\n===== REVIEW MENU =====");
            System.out.println("1. Give Review");
            System.out.println("2. View User Reviews");
            System.out.println("3. Back");
            System.out.print("Choose: ");
            int ch=sc.nextInt();

            switch (ch)
            {
                case 1:
                    giveReview(loggedInUser);
                    break;
                case 2:
                    viewReviews();
                    break;
                case 3:
                    return;
            }
        }
    }
    public void giveReview(User user)
    {
        try {


            System.out.print("Enter UserId to review : ");
            int reviewedId = sc.nextInt();

            System.out.print("(Rating 1-5) : ");
            int rating = sc.nextInt();
            sc.nextLine();

            System.out.print("Comment: ");
            String comment = sc.nextLine();


            Review review = new Review(user.getUserId(), reviewedId, rating, comment);

            if (reviewService.addReview(review)) {
                System.out.println("✅ Review submitted");
            } else {
                System.out.println("❌ Review failed");
            }
        }catch (NumberFormatException e)
        {
            System.out.println("❌ Please enter numeric values only for User ID and Rating.");

        }

    }
    public void viewReviews()
    {
        System.out.print("Enter the User ID :");
        int userId=sc.nextInt();
        sc.nextLine();

        List<Review> reviews=reviewService.getReviews(userId);
        double avg=reviewService.getAvgRating(userId);


        System.out.println("\n⭐ Average Rating: "+String.format("%.2f",avg));
        System.out.println("---- REVIEWS ----");

        if(reviews.isEmpty())
        {
            System.out.println("No Reviews Yet ....");
            return;
        }

        for(Review r:reviews)
        {
            System.out.println("Rating : "+r.getRating());
            System.out.println("comment : "+r.getComment());
            System.out.println("Date : "+r.getCreatedAt());
            System.out.println("---------------------");

        }
    }
}
