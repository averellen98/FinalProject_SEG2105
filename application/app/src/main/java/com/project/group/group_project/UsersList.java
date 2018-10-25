package com.project.group.group_project;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import android.content.Intent;

import android.os.Bundle;


public class UsersList extends AppCompatActivity {
    private Activity context;
    List<User> users;

    String userFirstName;
    String userRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        users = SignUpActivity.users;
        Intent intent = getIntent();
        userFirstName = intent.getStringExtra(MainActivity.USER_FIRSTNAME_KEY);
        userRole = intent.getStringExtra(MainActivity.USER_ROLE_KEY);

        String userAccounts = buildUserView(users);

        setUserView(userAccounts);

    }

    private String buildUserView(List<User> accounts) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < accounts.size(); i++){
            User lookUser = users.get(i);
            sb.append("User: " + lookUser.getFirstName().toString() + "; User Role: " + lookUser.getRole().toString() +". \n");
        }

        return sb.toString();
    }

    private void setUserView(String userAccounts) {
        TextView textView = findViewById(R.id.userAccounts);
        textView.setText(userAccounts);
    }



}
