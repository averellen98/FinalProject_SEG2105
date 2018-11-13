package com.project.group.group_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CreateProfileSPActivity extends Activity {

    private static final ProfileDatabase profileDatabase = ProfileDatabase.getInstance();

    private boolean isEdit;
    private String originalName;

    private TextView nameText;
    private TextView descriptionText;
    private TextView streetAddressText;
    private TextView postalCodeText;
    private TextView cityProvinceText;
    private TextView countryNameText;
    private RadioGroup licensedRadioGroup;
    private TextView phoneNumText;
    private Profile thisProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_serviceprovider_profile);

        nameText = findViewById(R.id.serviceNameText);
        descriptionText = findViewById(R.id.serviceDescriptionText);
        streetAddressText = findViewById(R.id.streetAddressText);
        postalCodeText = findViewById(R.id.postalCodeText);
        cityProvinceText = findViewById(R.id.cityProvinceText);
        countryNameText = findViewById(R.id.countryNameText);
        phoneNumText = findViewById(R.id.phoneNumText);
        licensedRadioGroup = findViewById(R.id.licensedRadioGroup);

    }

    public void onClickCreateProfile(View view) {

        String name = nameText.getText().toString();
        String description = descriptionText.getText().toString();
        String phoneNum = phoneNumText.getText().toString();
        String address = streetAddressText.getText().toString() + ", " + postalCodeText.getText().toString() + ", " +
                cityProvinceText.getText().toString() + ", " + countryNameText.getText().toString();
        String licensed = getRoleFromRadioButtonId(licensedRadioGroup.getCheckedRadioButtonId());

        if (isProfileValid(name, description, address, phoneNum, licensed)) {

            if (profileDatabase.isProfileAlreadyInDatabase(name)) {

                Toast.makeText(this, "Profile has already been created with that company.", Toast.LENGTH_LONG);

            } else {

                profileDatabase.addProfile(description, address, phoneNum, name, licensed, null);

                Intent intent = new Intent(this, ViewProfile.class);

                startActivity(intent);
            }
        }
    }

    private String getRoleFromRadioButtonId(int id) {

        RadioButton rb = findViewById(id);

        return rb.getText().toString();
    }

    private boolean isProfileValid(String companyName, String description, String address, String phoneNum, String licensed) {

        if  (companyName == null || companyName.isEmpty()) {
            Toast.makeText(this, "Please input company name.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (description == null || description.isEmpty()) {
            Toast.makeText(this, "Please input a description.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (address == null || address.isEmpty()) {
            Toast.makeText(this, "Please input an address.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (phoneNum == null || phoneNum.isEmpty()) {
            Toast.makeText(this, "Please input a phone number.", Toast.LENGTH_LONG).show();
            return false;
        }


        return true;
    }

}
