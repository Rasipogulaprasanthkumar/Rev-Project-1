package com.revconnect.service;

import com.revconnect.DAO.ReviewDAO;
import com.revconnect.DAO.UserDAO;
import com.revconnect.model.Review;

import java.util.List;

public class ReviewService
{
    private ReviewDAO reviewDAO= new ReviewDAO();
    private UserDAO userDAO=new UserDAO();
    public boolean addReview(Review review)
    {
        if(review.getReviewerId()==review.getReviewedUserId())
        {
            System.out.println("❌ You cannot review yourself");
            return false;
        }
        if (!userDAO.existsById(review.getReviewedUserId()))
        {
            System.out.println("❌ User ID does not exist");
            return false;
        }
        if(review.getRating()<1 || review.getRating()>5)
        {
            System.out.println("❌ Rating must be between 1 and 5");
            return false;
        }
        return reviewDAO.addReview(review);
    }
    public List<Review> getReviews(int userId)
    {
        return reviewDAO.getReviewByUser(userId);
    }
    public double getAvgRating(int userId)
    {
        return reviewDAO.getAverageRating(userId);
    }
}
