package com.project.group.group_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewProfile extends Activity{
    private ProfileDatabase profileDatabase = ProfileDatabase.getInstance();
    private ServiceDatabase serviceDatabase = ServiceDatabase.getInstance();

    private Profile profile;
    private String company;
    private TextView companyNameViewText;
    private TextView generalDescriptionViewText;
    private TextView phoneNumberViewText;
    private TextView addressViewText;
    private TextView licensedViewText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        companyNameViewText = findViewById(R.id.companyNameViewText);
        generalDescriptionViewText = findViewById(R.id.generalDescriptionViewText);
        phoneNumberViewText = findViewById(R.id.phoneNumberViewText);
        addressViewText = findViewById(R.id.addressViewText);
        licensedViewText = findViewById(R.id.licensedViewText);

        Intent intent = getIntent();
        company = intent.getStringExtra("companyName");
        profile = profileDatabase.getProfile(company);

        //figure out how to add services to the services tab on the profile

        companyNameViewText.setText(profile.getCompanyName());
        generalDescriptionViewText.setText(profile.getProfDescription());
        phoneNumberViewText.setText(profile.getPhoneNum());
        addressViewText.setText(profile.getProfAddress());
        licensedViewText.setText(profile.getLicensed());

    }

    public void viewServicesOnClick(View view){
        Intent intent = new Intent(this, ViewAddServicesActivity.class);
        intent.putExtra("profileName", company);
        startActivity(intent);
    }

    public void editAvailibilityOnClick(View view){
        Intent intent = new Intent(this, EditAvailabilityActivity.class);
        startActivity(intent);
    }
}
