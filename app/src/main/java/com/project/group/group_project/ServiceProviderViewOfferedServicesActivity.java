package com.project.group.group_project;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ServiceProviderViewOfferedServicesActivity extends Activity {

    private static final ServiceDatabase serviceDatabase = ServiceDatabase.getInstance();

    private String serviceProviderId;

    private List<Service> serviceProviderServices;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_view_offered_services);

        Intent intent = getIntent();
        serviceProviderId = intent.getStringExtra(Util.USER_ID);
        serviceProviderServices = serviceDatabase.getServiceForProvider(serviceProviderId);

        recyclerView = findViewById(R.id.servicesRecyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ServiceProviderViewOfferedServicesActivity.CustomAdapter();
        adapter.notifyDataSetChanged();

        recyclerView.setAdapter(adapter);
    }

    private class CustomAdapter extends RecyclerView.Adapter<SPViewServiceViewHolder> {

        @Override
        public SPViewServiceViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.sp_service_delete_row, viewGroup, false);

            return new SPViewServiceViewHolder(v);
        }

        @Override
        public void onBindViewHolder(SPViewServiceViewHolder viewHolder, final int position) {

            final Service service = serviceProviderServices.get(position);

            viewHolder.getTextView().setText(Util.buildServiceView(service));

            viewHolder.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    serviceDatabase.deleteServiceFromProvider(serviceProviderId, service.getId());
                    serviceProviderServices.remove(service);

                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return serviceProviderServices.size();
        }
    }

    private static class SPViewServiceViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private Button deleteButton;

        public SPViewServiceViewHolder(View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.textView);
            deleteButton = v.findViewById(R.id.addOrDeleteButton);
        }

        public TextView getTextView() {
            return textView;
        }

        public Button getDeleteButton() {
            return deleteButton;
        }
    }

    public void onClickDone(View view) {

        Intent intent = new Intent(this, ServiceProviderMainActivity.class);
        intent.putExtra(Util.USER_ID, serviceProviderId);
        startActivity(intent);
    }
}