package com.project.group.group_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
    }

    public void viewUsersOnClick(View view) {

        Intent intent = new Intent(this, AdminViewUsersActivity.class);
        startActivity(intent);
    }

    public void editServicesOnClick(View view) {

        Intent intent = new Intent(this, AdminViewServicesActivity.class);
        startActivity(intent);
    }

    public void logOutAdminOnClick(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}