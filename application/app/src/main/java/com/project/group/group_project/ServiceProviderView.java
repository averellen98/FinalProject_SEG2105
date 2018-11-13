package com.project.group.group_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ServiceProviderView extends AppCompatActivity {
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceprovider_view);

        Intent intent = getIntent();
        userName = intent.getStringExtra("username");
    }

    public void viewProfileOnClick(View view) {

        Intent intent = new Intent(this, ServiceProviderProfileExist.class);
        intent.putExtra("username", userName);
        startActivity(intent);
    }

    public void createProfileOnClick(View view) {

        Intent intent = new Intent(this, CreateProfileSPActivity.class);
        intent.putExtra("username", userName);
        startActivity(intent);
    }
}
