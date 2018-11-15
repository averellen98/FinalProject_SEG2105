package com.project.group.group_project;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AvailabilityActivity extends Activity {

    private static final AvailabilityDatabase availabilityDatabase = AvailabilityDatabase.getInstance();

    public static final String SERVICE_PROVIDER_ID = "service_provider_id";

    private String serviceProviderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        Intent intent = getIntent();
        serviceProviderId = intent.getStringExtra(SERVICE_PROVIDER_ID);

        List<Availability> availabilityList = availabilityDatabase.getAvailabilitiesByServiceProvider(serviceProviderId);

        if (!availabilityList.isEmpty()) {

            // TODO
            // the availability list is not empty, therefore we need to use the Availability objects to fill in the activity's values.
        }
    }

    public void saveAvailabilitiesOnClick(View view) {

        int sunStartHour = validateAndRetrieveHour(R.id.sunStartHourText);
        int sunStartMin = validateAndRetrieveMinute(R.id.sunStartMinuteText);
        int sunEndHour = validateAndRetrieveHour(R.id.sunEndHourText);
        int sunEndMin = validateAndRetrieveMinute(R.id.sunEndMinuteText);

        availabilityDatabase.addAvailability(serviceProviderId, Util.WeekDay.SUNDAY, sunStartHour, sunStartMin, sunEndHour, sunEndMin);

        int monStartHour = validateAndRetrieveHour(R.id.mondayStartHourText);
        int monStartMin = validateAndRetrieveMinute(R.id.mondayStartMinuteText);
        int monEndHour = validateAndRetrieveHour(R.id.mondayEndHour);
        int monEndMin = validateAndRetrieveMinute(R.id.mondayEndMinute);

        availabilityDatabase.addAvailability(serviceProviderId, Util.WeekDay.MONDAY, monStartHour, monStartMin, monEndHour, monEndMin);

        int tuesStartHour = validateAndRetrieveHour(R.id.tuesStartHour);
        int tuesStartMin = validateAndRetrieveMinute(R.id.tuesStartMinute);
        int tuesEndHour = validateAndRetrieveHour(R.id.tuesEndHour);
        int tuesEndMin = validateAndRetrieveMinute(R.id.tuesEndMinute);

        availabilityDatabase.addAvailability(serviceProviderId, Util.WeekDay.TUESDAY, tuesStartHour, tuesStartMin, tuesEndHour, tuesEndMin);

        int wedStartHour = validateAndRetrieveHour(R.id.wedStartHour);
        int wedStartMin = validateAndRetrieveMinute(R.id.wedStartMinute);
        int wedEndHour = validateAndRetrieveHour(R.id.wedEndHour);
        int wedEndMin = validateAndRetrieveMinute(R.id.wedEndMinute);

        availabilityDatabase.addAvailability(serviceProviderId, Util.WeekDay.WEDNESDAY, wedStartHour, wedStartMin, wedEndHour, wedEndMin);

        int thursStartHour = validateAndRetrieveHour(R.id.thursStartHour);
        int thursStartMin = validateAndRetrieveMinute(R.id.thursStartMinute);
        int thursEndHour = validateAndRetrieveHour(R.id.thursEndHour);
        int thursEndMin = validateAndRetrieveMinute(R.id.thursEndMinute);

        availabilityDatabase.addAvailability(serviceProviderId, Util.WeekDay.THURSDAY, thursStartHour, thursStartMin, thursEndHour, thursEndMin);

        int friStartHour = validateAndRetrieveHour(R.id.friStartHour);
        int friStartMin = validateAndRetrieveMinute(R.id.friStartMinute);
        int friEndHour = validateAndRetrieveHour(R.id.friEndHour);
        int friEndMin = validateAndRetrieveMinute(R.id.friEndMinute);

        availabilityDatabase.addAvailability(serviceProviderId, Util.WeekDay.FRIDAY, friStartHour, friStartMin, friEndHour, friEndMin);

        int satStartHour = validateAndRetrieveHour(R.id.satStartHour);
        int satStartMin = validateAndRetrieveMinute(R.id.satStartMinute);
        int satEndHour = validateAndRetrieveHour(R.id.satEndHour);
        int satEndMin = validateAndRetrieveMinute(R.id.satEndMinute);

        availabilityDatabase.addAvailability(serviceProviderId, Util.WeekDay.SATURDAY, satStartHour, satStartMin, satEndHour, satEndMin);
    }

    private int validateAndRetrieveHour(int id) {

        String hour = ((TextView) findViewById(id)).getText().toString();

        if (Util.validateHour(hour)) {
            return Integer.parseInt(hour);
        }

        Toast.makeText(this, "Hour is out of bounds", Toast.LENGTH_LONG).show();

        return 0;
    }

    private int validateAndRetrieveMinute(int id) {

        String minute = ((TextView) findViewById(id)).getText().toString();

        if (Util.validateHour(minute)) {
            return Integer.parseInt(minute);
        }

        Toast.makeText(this, "Minute is out of bounds", Toast.LENGTH_LONG).show();

        return 0;
    }
}