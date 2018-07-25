package com.example.kairo.kairoapp;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

/**
 * Created by kairo on 18/05/18.
 */

/**
 * * Loads a list of users by using an AsyncTask to perform the
 * * network request to the given URL.
 */
public class UserLoader extends AsyncTaskLoader<List<User>> {

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link UserLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public UserLoader(Context context, String url) {
        super( context );
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * * This is on a background thread.
     */
    @Override
    public List<User> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of users.
        List<User> users = QueryUtils.fetchUserData( mUrl );
        return users;
    }
}