package com.project.group.group_project;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeOwnerCreateBookingActivity extends Activity {

    private static final ServiceDatabase serviceDatabase = ServiceDatabase.getInstance();
    private static final BookingDatabase bookingDatabase = BookingDatabase.getInstance();

    private String serviceId;
    private String userId;

    private TextView bookingStartHourText;
    private TextView bookingStartMinuteText;
    private TextView bookingEndHourText;
    private TextView bookingEndMinuteText;
    private TextView bookingDayText;
    private TextView bookingMonthText;
    private TextView bookingYearText;

    private List<Availability> serviceAvailabilities;
    private List<Booking> bookings;
    private List<String> availableSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);

        Intent intent = getIntent();
        serviceId = intent.getStringExtra(Util.SERVICE_ID);
        userId = intent.getStringExtra(Util.USER_ID);

        bookingStartHourText = findViewById(R.id.bookingStartHourText);
        bookingStartMinuteText = findViewById(R.id.bookingStartMinuteText);
        bookingEndHourText = findViewById(R.id.bookingEndHourText);
        bookingEndMinuteText = findViewById(R.id.bookingEndMinuteText);
        bookingDayText = findViewById(R.id.bookingDayText);
        bookingMonthText = findViewById(R.id.bookingMonthText);
        bookingYearText = findViewById(R.id.bookingYearText);

        serviceAvailabilities = serviceDatabase.getAvailabilitiesForService(serviceId);
        bookings = bookingDatabase.getBookingList();

        String availabilityString = Util.buildAvailabilityString(serviceAvailabilities);

        ((TextView) findViewById(R.id.serviceProviderAvailabilityText)).setText(availabilityString);
    }

    public void cancelCreateBookingOnClick(View view) {

        Intent intent = new Intent(this, HomeOwnerSearchForServicesActivity.class);
        intent.putExtra(Util.USER_ID, userId);

        startActivity(intent);
    }

    public void createBookingOnClick(View view) {

        boolean isValidBooking = validateComponents();

        if (isValidBooking) {

            int startHour = Integer.parseInt(bookingStartHourText.getText().toString());
            int startMinute = Integer.parseInt(bookingStartMinuteText.getText().toString());
            int endHour = Integer.parseInt(bookingEndHourText.getText().toString());
            int endMinute = Integer.parseInt(bookingEndMinuteText.getText().toString());
            int day = Integer.parseInt(bookingDayText.getText().toString());
            int month = Integer.parseInt(bookingMonthText.getText().toString());
            int year = Integer.parseInt(bookingYearText.getText().toString());
            String serviceProviderId = getAvailableServiceProviderId(startHour, startMinute, endHour, endMinute, Util.getWeekDayForDate(day, month, year));

            bookingDatabase.addBooking(userId, serviceId, serviceProviderId, startHour, startMinute, endHour, endMinute, day, month, year);
            Intent intent = new Intent(this, HomeOwnerViewBookingsActivity.class);
            intent.putExtra(Util.USER_ID, userId);
            startActivity(intent);
        }
    }
//    //TODO fix this
//    private boolean openBookingTime(String serviceProviderId){
//
//        boolean isAvailable = false;
//
//        int startHour = Integer.parseInt(bookingStartHourText.getText().toString());
//        int startMinute = Integer.parseInt(bookingStartMinuteText.getText().toString());
//        int endHour = Integer.parseInt(bookingEndHourText.getText().toString());
//        int endMinute = Integer.parseInt(bookingEndMinuteText.getText().toString());
//        int day = Integer.parseInt(bookingDayText.getText().toString());
//        int month = Integer.parseInt(bookingMonthText.getText().toString());
//        int year = Integer.parseInt(bookingYearText.getText().toString());
//
//        for (Booking booking : bookings) {
//            if (booking.getServiceProviderId().equals(serviceProviderId)){
//                if (booking.getDay() == day && booking.getMonth() == month && booking.getYear() == year) {
//                    //booking is for an exact time already existing
//                    if (booking.getStartHour() == startHour && booking.getStartMinute() == startMinute && booking.getEndHour() == endHour && booking.getEndMinute() == endMinute) {
//                        isAvailable = false;
//                    }
//
//                    //start hour of new booking is the same as existing booking
//                    if (booking.getStartHour() == startHour) {
//                        if (booking.getStartMinute() < startMinute) {
//                            if (booking.getEndHour() == startHour) {
//                                if (booking.getEndMinute() <= startMinute) {
//                                    isAvailable = true;
//                                }
//                            }
//                        }
//                        if (startMinute < booking.getStartMinute()) {
//                            if (endHour == booking.getStartHour()) {
//                                if (endMinute <= booking.getStartMinute()) {
//                                    isAvailable = true;
//                                }
//                            }
//                        }
//                    }
//                    //start hour of existing booking is less than new booking
//                    if (booking.getStartHour() < startHour) {
//                        if (booking.getEndHour() < startHour) {
//                            isAvailable = true;
//                        }
//                        if (booking.getEndHour() == startHour) {
//                            if (booking.getEndMinute() <= startMinute) {
//                                isAvailable = true;
//                            }
//                        }
//                    }
//                    //start hour of new booking is less than existing booking
//                    if (startHour < booking.getStartHour()) {
//                        if (endHour < booking.getStartHour()) {
//                            isAvailable = true;
//                        }
//                        if (endHour == booking.getStartHour()) {
//                            if (endMinute <= booking.getStartMinute()) {
//                                isAvailable = true;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//
//        if (isAvailable == false) {
//            Toast.makeText(this, "There is not a service provider with that availability, please enter a different time slot.", Toast.LENGTH_LONG).show();
//            return false;
//        }
//
//        return true;
//
//
//    }

    private boolean validateComponents() {

        String startHour = ((TextView) findViewById(R.id.bookingStartHourText)).getText().toString();

        if (!Util.validateHour(startHour)) {

            Toast.makeText(this, "Start hour is invalid.", Toast.LENGTH_LONG).show();
            return false;
        }

        int startHH = Integer.parseInt(startHour);

        String startMinute = ((TextView) findViewById(R.id.bookingStartMinuteText)).getText().toString();

        if (!Util.validateMinute(startMinute)) {

            Toast.makeText(this, "Start minute is invalid.", Toast.LENGTH_LONG).show();
            return false;
        }

        int startMM = Integer.parseInt(startMinute);

        String endHour = ((TextView) findViewById(R.id.bookingEndHourText)).getText().toString();

        if (!Util.validateHour(endHour)) {

            Toast.makeText(this, "End hour is invalid.", Toast.LENGTH_LONG).show();
            return false;
        }

        int endHH = Integer.parseInt(endHour);

        String endMinute = ((TextView) findViewById(R.id.bookingEndMinuteText)).getText().toString();

        if (!Util.validateMinute(endMinute)) {

            Toast.makeText(this, "End hour is invalid.", Toast.LENGTH_LONG).show();
            return false;
        }

        int endMM = Integer.parseInt(endMinute);

        String dayString = ((TextView) findViewById(R.id.bookingDayText)).getText().toString();

        if (!Util.validateInteger(dayString)) {

            Toast.makeText(this, "Day is invalid.", Toast.LENGTH_LONG).show();
            return false;
        }

        int day = Integer.parseInt(dayString);

        String monthString = ((TextView) findViewById(R.id.bookingMonthText)).getText().toString();

        if (!Util.validateInteger(monthString)) {

            Toast.makeText(this, "Month is invalid.", Toast.LENGTH_LONG).show();
            return false;
        }

        int month = Integer.parseInt(monthString);

        if (!Util.validateMonth(month)) {

            Toast.makeText(this, "Month is out of range, please enter a month between 1-12.", Toast.LENGTH_LONG).show();
            return false;
        }

        String yearString = ((TextView) findViewById(R.id.bookingYearText)).getText().toString();

        if (!Util.validateInteger(yearString)) {

            Toast.makeText(this, "Year is invalid.", Toast.LENGTH_LONG).show();
            return false;
        }

        int year = Integer.parseInt(yearString);

        if (!Util.validateDay(day, month, year)) {

            Toast.makeText(this, "Day is invalid for the entered month and year.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!Util.validateDateHasNotPassed(day, month, year)) {

            Toast.makeText(this, "Date is today or has already passed, please enter a later date.", Toast.LENGTH_LONG).show();
            return false;
        }

        Util.WeekDay weekDayOfBooking = Util.getWeekDayForDate(day, month, year);

        if (!isAvailableTimeSlotForService(startHH, startMM, endHH, endMM, weekDayOfBooking)) {
            Toast.makeText(this, "There is not a service provider with that that time available, please enter a different time slot.", Toast.LENGTH_LONG).show();
            return false;
        }

//        if (!isThereAServiceProviderAvailableForBooking(day, month, year, startHH, startMM, endHH, endMM)) {
//            Toast.makeText(this, "All service providers are booked for that date and time, please enter a new one.", Toast.LENGTH_LONG).show();
//            return false;
//        } TODO

        return true;
    }

    private boolean isAvailableTimeSlotForService(int startHH, int startMM, int endHH, int endMM, Util.WeekDay weekDayOfBooking) {

        boolean availableSlotFound = false;

        for (Availability availability: serviceAvailabilities) {

            if (availability.getWeekDay().equals(weekDayOfBooking)) {

                availableSlotFound = availableSlotFound || timeIsWithinAvailability(availability, startHH, startMM, endHH, endMM);
            }
        }

        return availableSlotFound;
    }

    private boolean timeIsWithinAvailability(Availability availability, int startHH, int startMM, int endHH, int endMM) {

        if (availability.getStartHour() < startHH) {

            if (availability.getEndHour() > endHH) {
                return true;
            }

            if (availability.getEndHour() == endHH && availability.getEndMinute() >= endMM) {
                return true;
            }
        }

        if (availability.getStartHour() == startHH && availability.getStartMinute() <= startMM) {

            if (availability.getEndHour() > endHH) {
                return true;
            }

            if (availability.getEndHour() == endHH && availability.getEndMinute() >= endMM) {
                return true;
            }
        }

        return false;
    }

    private boolean isThereAServiceProviderAvailableForBooking(int day, int month, int year, int startHH, int startMM, int endHH, int endMM) {

        List<Booking> currentBookingsList = BookingDatabase.getInstance().getBookingList();

        boolean spAvailable = false;

        for (Booking booking: currentBookingsList) {

            if (booking.getServiceId().equals(serviceId)) {

                if (booking.getYear() == year && booking.getMonth() == month && booking.getDay() == day) {

                }
            }

//                List<String> serviceProviderIdsThatOfferService = ServiceDatabase.getInstance().getAllSPIDByServiceId(serviceId);
//
//                for (String spId: serviceProviderIdsThatOfferService) {
//
//                    if (booking.getServiceProviderId().equals(spId)) {
//
//                        if (booking.getDay() == day && booking.getMonth() == month && booking.getYear() == year) {
//
//                            if (!isBookingOverlapping(booking, startHH, startMM, endHH, endMM)) {
//                                spAvailable = true;
//                            }
//                        }
//                    }
//
//                    if (isServiceProviderAvailable(booking.getServiceProviderId(), day, month, year, startHH, startMM, endHH, endMM)) {
//                        return true;
//                    }
//                }

        }

        return spAvailable;
    }

    private boolean isServiceProviderAvailable(String spId, int day, int month, int year, int startHH, int startMM, int endHH, int endMM) {

        Util.WeekDay weekDay = Util.getWeekDayForDate(day, month, year);

        List<Availability> serviceProviderAvailabilites = AvailabilityDatabase.getInstance().getAvailabilitiesByServiceProvider(spId);

        for (Availability availability: serviceProviderAvailabilites) {

            if (availability.getWeekDay().equals(weekDay) && timeIsWithinAvailability(availability, startHH, startMM, endHH, endMM)) {
                return true;
            }
        }

        return false;
    }


    private String getAvailableServiceProviderId(int startHH, int startMM, int endHH, int endMM, Util.WeekDay weekDayOfBooking) {

        for (Availability availability: serviceAvailabilities) {

            if (availability.getWeekDay().equals(weekDayOfBooking)) {

                if (timeIsWithinAvailability(availability, startHH, startMM, endHH, endMM)) {

                    if (!isServiceProviderBooked(availability.getServiceProviderId(), startHH, startMM, endHH, endMM, availability)) {

                        return availability.getServiceProviderId();
                    }
                }
            }
        }

        return null;
    }

    private boolean isServiceProviderBooked(String spId, int startHH, int startMM, int endHH, int endMM, Availability availability) {

        List<Booking> bookingsList = BookingDatabase.getInstance().getBookingList();

        for (Booking booking: bookingsList) {

            if (booking.getServiceProviderId().equals(spId)) {

                Util.WeekDay weekday = Util.getWeekDayForDate(booking.getDay(), booking.getMonth(), booking.getYear());

                if (weekday.equals(availability.getWeekDay())) {

                    if (isBookingOverlapping(booking, startHH, startMM, endHH, endMM)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isBookingOverlapping(Booking booking, int startHH, int startMM, int endHH, int endMM) {

        if (booking.getStartHour() < startHH) {

            if (booking.getEndHour() > startHH) {
                // The booking starts before, and ends after the start hour. overlap.
                return false;
            }
        } else if (booking.getStartHour() == startHH) {

            if (booking.getStartMinute() <= startMM) {

                if (booking.getEndHour() < endHH) {

                    if (booking.getEndMinute() > startMM) {
                        // same start hour, booking starts first, but does not end before the start minute.
                        return false;
                    }
                }
            } else if (booking.getEndHour() >= endHH){
                //booking starts at same hour, but afterwards minute wise, and ends after.
                return false;
            } else {
                //booking end hour is before this end hour, start hour is equal.
                return false;
            }
        } else if (booking.getStartHour() > startHH) {

            if (booking.getEndHour() <= endHH) {
                // booking time is inside the times.
                return false;
            } else if (booking.getEndHour() > endHH) {
                //no overlap
            }
        }

        return false;

    }
}