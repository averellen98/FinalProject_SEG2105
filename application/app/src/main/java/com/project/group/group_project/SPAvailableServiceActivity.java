package com.project.group.group_project;

import android.os.Bundle;
import android.app.Activity;

public class SPAvailableServiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spavailable_service);

        // TODO
        // this activity gathers all services not already attached to this user and displays them.
        // each service should have an add button that when pressed, adds the service to the user,
        // adds the relationship to the database, and removes that services from the view.
    }

}
