package com.example.kairo.kairoapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
/**
 * Created by kairo on 16/05/18.
 */



public class Users extends Fragment implements LoaderManager.LoaderCallbacks<List<User>>{
    /**
     * Adapter for the list of users
     */
    private UserAdapter mAdapter;

    /**
     * URL for users data from the USGS dataset
     */
    private static final String USGS_REQUEST_URL =
            "https://jsonplaceholder.typicode.com/posts";

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int USER_LOADER_ID = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*super.onCreate( savedInstanceState );
        getActivity().setContentView( R.layout.users_tab );*/

        View RootView = inflater.inflate(R.layout.users_tab, container, false);

        //TextView tv = (TextView)RootView.findViewById(R.id.fragmentText);

        // Find a reference to the {@link ListView} in the layout
        ListView userListView = (ListView) RootView.findViewById(R.id.list);

        // Create a new adapter that takes an empty list of users as input
        mAdapter = new UserAdapter(this.getContext(), new ArrayList<User>() );

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        userListView.setAdapter( mAdapter );

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        userListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                User currentUser = mAdapter.getItem( position );

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri userUri = Uri.parse( currentUser.getUrl() );

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent( Intent.ACTION_VIEW, userUri );

                // Send the intent to launch a new activity
                startActivity( websiteIntent );
            }
        } );

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService( Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(USER_LOADER_ID, null, this);
        } /*else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = getActivity().findViewById( R.id.loading_indicator );
            loadingIndicator.setVisibility( View.GONE );

            // Update empty state with no connection error message
            //mEmptyStateTextView.setText( R.string.no_internet_connection );
        }*/

        return RootView;
    }

    @Override
    public Loader<List<User>> onCreateLoader(int i, Bundle bundle) {


        // Create a new loader for the given URL
        return new UserLoader( getActivity().getApplicationContext(), USGS_REQUEST_URL );
    }

    @Override
    public void onLoadFinished(Loader<List<User>> loader, List<User> users) {

        // Clear the adapter of previous user data
        mAdapter.clear();
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (users != null && !users.isEmpty()) {
            mAdapter.addAll( users );
        }
    }

    @Override
    public void onLoaderReset(Loader<List<User>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}