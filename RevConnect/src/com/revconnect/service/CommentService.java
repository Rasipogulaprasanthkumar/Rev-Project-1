package com.revconnect.service;

import com.revconnect.DAO.CommentDAO;
import com.revconnect.model.Comment;

import java.util.List;

public class CommentService
{
    private CommentDAO commentDAO=new CommentDAO();

    public boolean addComment(Comment comment)
    {
        if(comment.getComment()==null||comment.getComment().isBlank())
        {
            System.out.println("❌ Comment cannot be empty");
            return false;
        }
        return commentDAO.addComment(comment);
    }
    public List<Comment> getComments(int postId)
    {
        return commentDAO.getCommentsByPost(postId);
    }
}
