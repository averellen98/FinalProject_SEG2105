package com.project.group.group_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceProviderProfileExist extends AppCompatActivity {

    private TextView companyName;

    private ProfileDatabase profileDatabase = ProfileDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_profile_exists);

        companyName = findViewById(R.id.companyNameText);
    }


    public void checkProfileExistsOnClick(View view) {

        Profile profile;
        String name = companyName.getText().toString();

        if (profileDatabase.isProfileAlreadyInDatabase(name)){

            Toast.makeText(this, "Profile found.", Toast.LENGTH_SHORT).show();

            profile = profileDatabase.getProfile(name);

            if (profile != null) {

                Intent intent = new Intent(this, ViewProfile.class);
                intent.putExtra("companyName", profile.getCompanyName());

                startActivity(intent);
            }

        } else {
            Toast.makeText(this, "Profile not found, please start one", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, CreateProfileSPActivity.class);
            startActivity(intent);
        }
    }

}
