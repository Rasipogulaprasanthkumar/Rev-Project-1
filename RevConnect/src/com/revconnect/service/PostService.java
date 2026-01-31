package com.revconnect.service;

import com.revconnect.DAO.PostDAO;
import com.revconnect.model.Like;
import com.revconnect.model.Post;

import java.util.List;

public class PostService
{
    private PostDAO postDAO=new PostDAO();
    public boolean createPost(Post post)
    {
        if(post.getContent()==null || post.getContent().isBlank())
        {
            System.out.println("❌ Post cannot be empty");
            return false;
        }
        return postDAO.createPost(post);
    }
    public List<Post> getAllPosts()
    {
        return postDAO.getAllPosts();
    }
}
