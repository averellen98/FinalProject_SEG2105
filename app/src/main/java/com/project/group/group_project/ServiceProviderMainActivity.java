package com.project.group.group_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ServiceProviderMainActivity extends AppCompatActivity {

    private String serviceProviderId;
    private ServiceDatabase serviceDatabase = ServiceDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_main);

        Intent intent = getIntent();
        serviceProviderId = intent.getStringExtra(Util.USER_ID);
    }

    public void viewAvailabilitiesOnClick(View view) {

        Intent intent = new Intent(this, ServiceProviderAvailabilityActivity.class);
        intent.putExtra(Util.USER_ID, serviceProviderId);

        startActivity(intent);
    }

    public void viewAvailableServicesOnClick(View view) {

        Intent intent = new Intent(this, ServiceProviderAvailableServiceActivity.class);
        intent.putExtra(Util.USER_ID, serviceProviderId);

        startActivity(intent);
    }

    public void viewOfferedServicesOnClick(View view) {

        Intent intent = new Intent(this, ServiceProviderViewOfferedServicesActivity.class);
        intent.putExtra(Util.USER_ID, serviceProviderId);

        startActivity(intent);
    }

    public void logOutOfServicePOnClick(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
