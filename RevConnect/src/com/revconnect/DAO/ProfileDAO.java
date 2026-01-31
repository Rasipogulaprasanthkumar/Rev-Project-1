package com.revconnect.DAO;

import com.revconnect.model.Profile;
import com.revconnect.util.DBConnection;

import java.sql.*;

public class ProfileDAO
{
    public boolean createProfile(Profile p)
    {
        String sql="Insert into profiles(user_id,name,bio,location,website,category,business_address)values(?,?,?,?,?,?,?)";

        try(Connection con= DBConnection.getConnection();
        PreparedStatement pst=con.prepareStatement(sql))
        {
            pst.setInt(1,p.getUserId());
            pst.setString(2,p.getName());
            pst.setString(3,p.getBio());
            pst.setString(4,p.getLocation());
            pst.setString(5, p.getWebsite());
            pst.setString(6,p.getCategory());
            pst.setString(7,p.getBusinessAddress());
            return pst.executeUpdate()>0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Profile getProfileById(int userId)
    {
        try(Connection con=DBConnection.getConnection();
        PreparedStatement pst=con.prepareStatement("select * from profiles where user_id=?"))
        {
            pst.setInt(1,userId);
            ResultSet rs=pst.executeQuery();
            Profile p=new Profile();
            while(rs.next())
            {
                p.setProfile_id(rs.getInt("profile_id"));
                p.setUserId(rs.getInt("user_id"));
                p.setName(rs.getString("name"));
                p.setBio(rs.getString("bio"));
                p.setLocation(rs.getString("location"));
                p.setWebsite(rs.getString("website"));
                p.setCategory(rs.getString("category"));
                p.setBusinessAddress(rs.getString("business_address"));
                return p;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateProfile(Profile p) {

        String sql = "UPDATE profiles SET name=?, bio=?, location=?, website=?, category=?, business_address=? WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getBio());
            ps.setString(3, p.getLocation());
            ps.setString(4, p.getWebsite());
            ps.setString(5, p.getCategory());
            ps.setString(6, p.getBusinessAddress());
            ps.setInt(7, p.getUserId());

            return ps.executeUpdate() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
