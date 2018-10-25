package com.project.group.group_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    String userFirstName;
    String userRole;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();
        userFirstName = intent.getStringExtra(MainActivity.USER_FIRSTNAME_KEY);
        userRole = intent.getStringExtra(MainActivity.USER_ROLE_KEY);
        continueButton = (Button) findViewById(R.id.continueButton);

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

    public void continueButtonOnClick(View view){

        String role = userRole.toString();
        Toast.makeText(this, role.toString(),Toast.LENGTH_SHORT).show();

        if (role.toString().equals("Admin")){
            Intent intent2 = new Intent(this, AdminView.class);
            startActivity(intent2);
            setContentView(R.layout.activity_admin_view);
        } else if (role.toString().equals("Home Owner")){
            Intent intent3 = new Intent(this, HomeOwnerView.class);
            startActivity(intent3);
            setContentView(R.layout.activity_not_implemented);
        } else if (role.toString().equals("Service Provider")){
            Intent intent4 = new Intent(this, ServiceProviderView.class);
            startActivity(intent4);
            setContentView(R.layout.activity_not_implemented);
        }

    }
}
