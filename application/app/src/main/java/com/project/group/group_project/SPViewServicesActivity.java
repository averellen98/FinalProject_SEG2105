package com.project.group.group_project;

import android.os.Bundle;
import android.app.Activity;

public class SPViewServicesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spview_services);

        //TODO
        //this activity should gather all services connected to the user an display them.
        //each service should have a delete button that when pressed remove the service from the user,
        // removes the relationship from the db, and removes it from the view.
    }

}
