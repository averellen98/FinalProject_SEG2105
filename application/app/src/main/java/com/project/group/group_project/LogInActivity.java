package com.project.group.group_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class LogInActivity extends AppCompatActivity {

    private TextView email;
    private TextView password;
    public static List<User> theUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
    }

    public void logInOnClick(View view) {

        User user = null;
        String u_email = email.getText().toString();
        String u_password = password.getText().toString();

        theUsers = SignUpActivity.users;
        for (int i = 0; i < theUsers.size(); i++){
            User thisUser = theUsers.get(i);
            if (thisUser.getEmail() == u_email && thisUser.getPassword() == u_password){
                //check if this user exists
                Toast.makeText(this, "User found.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, WelcomeActivity.class);
                intent.putExtra(MainActivity.USER_FIRSTNAME_KEY, user.getFirstName());
                intent.putExtra(MainActivity.USER_ROLE_KEY, user.getRole().getName());

                startActivity(intent);
            }
            if (i == theUsers.size()){
                //need to implement message to user if their account was not found instead of going to next activity.
                Toast.makeText(this, "User not found, please try again.", Toast.LENGTH_SHORT).show();
                email.setText("");
                password.setText("");
            }
        }




    }
}
