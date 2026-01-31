package com.revconnect.DAO;

import com.revconnect.model.Comment;
import com.revconnect.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    public boolean addComment(Comment comment) {
        String sql = "INSERT INTO comments(post_id, user_id, comment) VALUES (?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, comment.getPostId());
            pst.setInt(2, comment.getUserId());
            pst.setString(3, comment.getComment());

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Comment> getCommentsByPost(int postId) {
        List<Comment> list = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE post_id=? ORDER BY created_at";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, postId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Comment c = new Comment();
                c.setCommentId(rs.getInt("comment_id"));
                c.setPostId(rs.getInt("post_id"));
                c.setUserId(rs.getInt("user_id"));
                c.setComment(rs.getString("comment"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
