package com.example.kairo.kairoapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;


/**
 * Created by kairo on 17/05/18.
 */

public class UserAdapter extends ArrayAdapter<User> {

    /**
     * @param context     of the app
     * @param users is the list of users which is the data source of the adapter
     */
    public UserAdapter(Context context, List<User> users) {
        super( context, 0, users );
    }

    @NonNull
    @Override

    /**
     * Returns a list item view that displays information about user at the given position
     * in the list of users
     * */
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from( getContext() ).inflate( R.layout.user_list_item,
                    parent, false );
        }

        // Find the user at the given position in the list of users
        User currentUser = getItem( position );

        TextView userIdView = (TextView) listItemView.findViewById( R.id.userId );
        int userId = currentUser.getUserId();
        String userID = formatId(userId);
        userIdView.setText( "User id is: "+ userID );

        TextView idView = (TextView) listItemView.findViewById( R.id.id );
        int id = currentUser.getId();
        String ID = formatId(id);
        idView.setText( "Id is: "+ ID );

        TextView titleView = (TextView) listItemView.findViewById( R.id.title );
        String  title = currentUser.gettitle();
        titleView.setText( "Title is: "+ title );

        TextView bodyView = (TextView) listItemView.findViewById( R.id.body );
        String  body = currentUser.getBody();
        bodyView.setText( "Body is: "+ body );

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
    private String formatId(int id) {
        return Integer.toString(id);
    }
}
