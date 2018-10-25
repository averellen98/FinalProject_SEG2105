package com.project.group.group_project;

import android.support.v7.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.widget.AdapterView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;


public class AdminView extends AppCompatActivity {
    String userFirstName;
    String userRole;
    ListView listViewUsers;
    DatabaseReference databaseUsers;
    Button viewUsers;
    Button editServices;

    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this,"admin view",Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        Intent intent = getIntent();
        userFirstName = intent.getStringExtra(MainActivity.USER_FIRSTNAME_KEY);
        userRole = intent.getStringExtra(MainActivity.USER_ROLE_KEY);


    }

    public void viewUsersOnClick(View view) {
        Intent intent = new Intent(this, Services.class);
        intent.putExtra(MainActivity.USER_FIRSTNAME_KEY, userFirstName);
        intent.putExtra(MainActivity.USER_ROLE_KEY, userRole);
        startActivity(intent);
        setContentView(R.layout.activity_not_implemented);
    }

    public void editServicesOnClick(View view) {
        Intent intent = new Intent(this, Services.class);
        startActivity(intent);
        setContentView(R.layout.activity_not_implemented);
    }

}


