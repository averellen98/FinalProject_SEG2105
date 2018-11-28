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

public class HomeOwnerServiceListForRatingActivity extends Activity{

    private static final ServiceDatabase serviceDatabase = ServiceDatabase.getInstance();

    private String userId;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_view_services);

        Intent intent = getIntent();
        userId = intent.getStringExtra(Util.USER_ID);

        recyclerView = findViewById(R.id.servicesRecyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new HomeOwnerServiceListForRatingActivity.CustomAdapter();
        adapter.notifyDataSetChanged();

        recyclerView.setAdapter(adapter);
    }

    public void backOnClick(View view) {

        Intent intent = new Intent(this, HomeOwnerMainActivity.class);
        intent.putExtra(Util.USER_ID, userId);

        startActivity(intent);
    }

    private class CustomAdapter extends RecyclerView.Adapter<HomeOwnerRateServiceViewHolder> {

        @Override
        public HomeOwnerRateServiceViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.home_owner_rate_service_row, viewGroup, false);

            return new HomeOwnerRateServiceViewHolder(v);
        }

        @Override
        public void onBindViewHolder(HomeOwnerRateServiceViewHolder viewHolder, final int position) {

            final Service service = serviceDatabase.getAllServices().get(position);

            viewHolder.getTextView().setText(Util.buildServiceView(service));

            viewHolder.getRateButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    doRate(service);
                }
            });
        }

        @Override
        public int getItemCount() {
            return serviceDatabase.getAllServices().size();
        }
    }

    private void doRate(Service service) {

        Intent intent = new Intent(this, HomeOwnerCreateServiceRatingActivity.class);
        intent.putExtra(Util.SERVICE_ID, service.getId());
        intent.putExtra(Util.USER_ID, userId);

        startActivity(intent);
    }

    private static class HomeOwnerRateServiceViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private Button rateButton;

        public HomeOwnerRateServiceViewHolder(View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.textView);
            rateButton = v.findViewById(R.id.rateButton);
        }

        public TextView getTextView() {
            return textView;
        }

        public Button getRateButton() {
            return rateButton;
        }

    }
}