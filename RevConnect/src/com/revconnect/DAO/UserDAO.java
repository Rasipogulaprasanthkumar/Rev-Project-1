package com.revconnect.DAO;

import com.revconnect.model.User;
import com.revconnect.util.DBConnection;
import java.sql.*;

public class UserDAO
{
    //Register

    public boolean register(User user)
    {
        String sql="insert into users(username,email,password,role) values(?,?,?,?)";
        try(Connection con= DBConnection.getConnection();
        PreparedStatement pst= con.prepareStatement(sql)) {
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getRole());
            System.out.println("👉 Trying to insert: " + user.getEmail());

            return pst.executeUpdate() > 0;
        }catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Email or username already exists ❌");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public User findByEmail(String email)
    {
        String sql="select * from users where email=?";
        try(Connection con=DBConnection.getConnection();
        PreparedStatement pst=con.prepareStatement(sql))
        {
            pst.setString(1,email);
            ResultSet rs=pst.executeQuery();


            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                return u;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }



    //For the Reviews
    public boolean existsById(int userId) {

        String sql = "SELECT user_id FROM users WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
