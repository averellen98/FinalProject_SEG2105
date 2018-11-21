package com.project.group.group_project;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class HomeOwnerSearchServices extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_search_services);
    }

    public void searchForServicesOnClick(View view) {

        // TODO implement this

        // if none of the fields are filled, show all services

        // if a field is empty, ignore it, otherwise search using the AND operator.
        // first field is serviceName, second fields needs some magic, and third field needs some as well.

        // for second fields: Find ServiceProvider with hours within the boundary hours, then use the services they provide.

        // for third field: gather all the ratings for the service, then calculate the average. If the average (as an int) is the same then return this service.
    }
}