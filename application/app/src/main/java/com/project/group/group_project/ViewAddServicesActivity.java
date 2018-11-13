package com.project.group.group_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewAddServicesActivity extends Activity {
    private ProfileDatabase profileDatabase = ProfileDatabase.getInstance();
    private ServiceDatabase serviceDatabase = ServiceDatabase.getInstance();
    private Profile profile;
    private String company;
    private List<Service> servicesAvail = new ArrayList<Service>();
    private String toAddService;

    private TextView serviceText;
    private TextView servicesListText;
    private String serviceBuild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_add_profile);

        Intent intent = getIntent();
        company = intent.getStringExtra("companyName");
        profile = profileDatabase.getProfile(company);
        servicesAvail = profile.getServicesAvail();

        serviceText = findViewById(R.id.serviceText);
        servicesListText = findViewById(R.id.servicesListText);

        toAddService = serviceText.getText().toString();
        serviceBuild = buildServices(0);
        servicesListText.setText(serviceBuild);
    }

    private String buildServices(int index) {

        StringBuilder sb = new StringBuilder();

        String n = "\r\n";

        Service service = servicesAvail.get(index);

        sb.append("Service Name: " + service.getName() + n);
        sb.append("Service Description: " + service.getDescription() + n);
        sb.append("Service Rate Per Hour:" + service.getRatePerHour() + n);

        return sb.toString();
    }

    public void backOnClick(View view) {
        Intent intent = new Intent(this, ViewProfile.class);
        startActivity(intent);
    }

    public void addServiceProfileOnClick(View view) {
        if (serviceDatabase.isServiceAlreadyInDatabase(toAddService)) {
            Service serviceToAdd = serviceDatabase.getService(toAddService);
            if (serviceToAdd != null) {
                servicesAvail.add(serviceToAdd);
                serviceBuild = buildServices(0);
                servicesListText.setText(serviceBuild);
            }
        }
        else {
            Toast.makeText(this, "Service is not available. Please try again", Toast.LENGTH_SHORT).show();
            serviceText.setText("");
        }
    }

    public void deleteServiceProfileOnClick(View view){
        if (serviceDatabase.isServiceAlreadyInDatabase(toAddService)) {
            Service serviceToAdd = serviceDatabase.getService(toAddService);
            if (serviceToAdd != null) {
                servicesAvail.remove(serviceToAdd);
                serviceBuild = buildServices(0);
                servicesListText.setText(serviceBuild);
            }
        }
        else {
            Toast.makeText(this, "Service is not available. Please try again", Toast.LENGTH_SHORT).show();
            serviceText.setText("");
        }
    }



}
 
