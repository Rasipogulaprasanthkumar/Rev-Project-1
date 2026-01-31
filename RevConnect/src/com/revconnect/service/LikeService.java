package com.revconnect.service;

import com.revconnect.DAO.LikeDAO;

public class LikeService
{
    private LikeDAO likeDAO=new LikeDAO();
    public boolean likePost(int postId,int userId)
    {
        return likeDAO.likePost(postId,userId);
    }
    public int countLikes(int postId)
    {
        return likeDAO.countLikes(postId);
    }
}
