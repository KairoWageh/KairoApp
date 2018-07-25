package com.example.kairo.kairoapp;

/**
 * Created by kairo on 17/05/18.
 */

public class User {

    // userId
    private int mUserId;

    // id
    private int mId;

    // title
    private String mTitle;

    // body
    private String mBody;

    //url
    private String mUrl;


    /**
     * Constructs a nes {@link User} object
     *
     * @param userId
     * @param id
     * @param title
     * @param body
     */
    public User(int userId, int id, String title, String body) {
        mUserId = userId;
        mId = id;
        mTitle = title;
        mBody = body;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return mUserId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return mId;
    }

    /**
     * @return the title
     */
    public String gettitle() {
        return mTitle;
    }

    /**
     * Returns the body
     */
    public String getBody() {
        return mBody;
    }

    /**
     * Returns the website URL to find more information about the earthquake.
     */
    public String getUrl() {
        return mUrl;
    }
}
