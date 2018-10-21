package com.project.group.group_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();
        String userFirstName = intent.getStringExtra(MainActivity.USER_FIRSTNAME_KEY);
        String userRole = intent.getStringExtra(MainActivity.USER_ROLE_KEY);

        String welcomeMessage = buildWelcomeMessage(userFirstName, userRole);

        setWelcomeText(welcomeMessage);
    }

    private String buildWelcomeMessage(String userFirstName, String userRole) {

        StringBuilder sb = new StringBuilder();

        sb.append("Welcome " + userFirstName + "!\r\n");
        sb.append("You are logged in as " + userRole);

        return sb.toString();
    }

    private void setWelcomeText(String welcomeMessage) {

        TextView textView = findViewById(R.id.welcomeMessageText);
        textView.setText(welcomeMessage);
    }
}