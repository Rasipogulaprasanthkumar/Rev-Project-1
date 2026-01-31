package com.revconnect.DAO;

import com.revconnect.util.DBConnection;

import java.sql.*;

    public class LikeDAO {

        public boolean likePost(int postId, int userId) {
            String sql = "INSERT INTO post_likes(post_id, user_id) VALUES (?,?)";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setInt(1, postId);
                pst.setInt(2, userId);

                return pst.executeUpdate() > 0;

            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("⚠️ You already liked this post");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        public int countLikes(int postId) {
            String sql = "SELECT COUNT(*) FROM post_likes WHERE post_id=?";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setInt(1, postId);
                ResultSet rs = pst.executeQuery();

                if (rs.next())
                    return rs.getInt(1);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
