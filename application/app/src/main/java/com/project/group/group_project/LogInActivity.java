package com.project.group.group_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {

    private TextView email;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
    }

    public void logInOnClick(View view) {

        User user = null;

        //code to get user from db using email and password

        //need to implement message to user if their account was not found instead of going to next activity.

        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra(MainActivity.USER_FIRSTNAME_KEY, user.getFirstName());
        intent.putExtra(MainActivity.USER_ROLE_KEY, user.getRole().getName());

        startActivity(intent);
    }
}
