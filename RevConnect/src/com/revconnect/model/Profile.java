package com.revconnect.model;

public class Profile
{
    private int profile_id;
    private int userId;
    private String name;
    private String bio;
    private String location;
    private String website;
    private String category;
    private String businessAddress;

    public Profile()
    {

    }
    public Profile(int profile_id, int userId, String name, String bio, String location, String website, String category, String businessAddress) {
        this.profile_id = profile_id;
        this.userId = userId;
        this.name = name;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.category = category;
        this.businessAddress = businessAddress;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }


}
