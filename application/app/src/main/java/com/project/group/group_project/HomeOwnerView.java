package com.project.group.group_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeOwnerView extends AppCompatActivity {

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_view);

        Intent intent = getIntent();
        userId = intent.getStringExtra(Util.USER_ID);
    }

    public void searchForServiceOnClick(View view) {

        // TODO implement this opening the next intent
    }

    public void viewBookingsOnClick(View view) {

        // TODO implement this opening the next intent
    }
}