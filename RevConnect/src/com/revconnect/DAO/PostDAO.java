package com.revconnect.DAO;

import com.revconnect.model.Post;
import com.revconnect.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDAO
{
    public boolean createPost(Post post)
    {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement("insert into posts (user_id,content) values (?,?);"))
        {
            pst.setInt(1,post.getUserId());
            pst.setString(2,post.getContent());

            return pst.executeUpdate()>0;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT * FROM posts ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Post p = new Post();
                p.setPostId(rs.getInt("post_id"));
                p.setUserId(rs.getInt("user_id"));
                p.setContent(rs.getString("content"));
                p.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
