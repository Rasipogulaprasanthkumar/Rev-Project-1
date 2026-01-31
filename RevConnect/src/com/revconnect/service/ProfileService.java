package com.revconnect.service;

import com.revconnect.DAO.ProfileDAO;
import com.revconnect.model.Profile;

public class ProfileService
{
    private ProfileDAO profileDAO=new ProfileDAO();
    public boolean createProfile(Profile profile)
    {
        return profileDAO.createProfile(profile);
    }
    public Profile viewProfile(int userId)
    {
        return profileDAO.getProfileById(userId);
    }
    public boolean editProfile(Profile profile)
    {
        return profileDAO.updateProfile(profile);
    }
}

