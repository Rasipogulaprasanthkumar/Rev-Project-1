package com.revconnect.DAO;

//import com.mysql.cj.util.DnsSrv;
import com.revconnect.model.Review;
import com.revconnect.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO
{
    public boolean addReview(Review review)
    {
        try(Connection con= DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("insert into reviews(reviewer_id,reviewed_user_id,rating,comment)values (?,?,?,?)"))
        {
            pst.setInt(1,review.getReviewerId());
            pst.setInt(2,review.getReviewedUserId());
            pst.setInt(3,review.getRating());
            pst.setString(4,review.getComment());
            return pst.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Review> getReviewByUser(int userId)
    {
        List<Review> list=new ArrayList<>();
        String sql="select * from reviews where reviewed_user_id=? ORDER by created_At desc";
        try(Connection con=DBConnection.getConnection();
        PreparedStatement pst=con.prepareStatement(sql))
        {
            pst.setInt(1,userId);
           ResultSet rs= pst.executeQuery();

           while(rs.next())
           {
               Review r=new Review();
               r.setReviewId(rs.getInt("review_id"));
               r.setReviewerId(rs.getInt("reviewer_id"));
               r.setReviewedUserId(rs.getInt("reviewed_user_id"));
               r.setRating(rs.getInt("rating"));
               r.setComment(rs.getString("comment"));
               r.setCreatedAt(rs.getTimestamp("created_At"));
               list.add(r);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public double getAverageRating(int userId)
    {

        try(Connection con= DBConnection.getConnection();
        PreparedStatement pst=con.prepareStatement("select AVG(rating) As avg_rating from reviews where reviewed_user_id=?")) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                return rs.getDouble("avg_rating");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
