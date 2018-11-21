package com.project.group.group_project;

import android.os.Bundle;
import android.app.Activity;

public class HomeOwnerFoundServiceList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_found_service_list);
    }

    //TODO this will display the services found in HomeOwnerSearchServices

    //Each service should use layout: home_owner_service_row.xml
        //When the user clicks the "book" button of a service, launch the HomeOwnerCreateBooking intent

}