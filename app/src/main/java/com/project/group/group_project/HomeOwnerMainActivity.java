package com.project.group.group_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeOwnerMainActivity extends AppCompatActivity {

    private String userId;
    private BookingDatabase bkdb = BookingDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_main);

        Intent intent = getIntent();
        userId = intent.getStringExtra(Util.USER_ID);
    }

    public void searchForServiceOnClick(View view) {

        Intent intent = new Intent(this, HomeOwnerSearchForServicesActivity.class);
        intent.putExtra(Util.USER_ID, userId);

        startActivity(intent);
    }

    public void viewBookingsOnClick(View view) {

        Intent intent = new Intent(this, HomeOwnerViewBookingsActivity.class);
        intent.putExtra(Util.USER_ID, userId);

        startActivity(intent);
    }

    public void rateServicesOnClick(View view) {

        Intent intent = new Intent(this, HomeOwnerServiceListForRatingActivity.class);
        intent.putExtra(Util.USER_ID, userId);

        startActivity(intent);
    }

    public void logOutHOOnClick(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
