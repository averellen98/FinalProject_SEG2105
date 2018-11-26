package com.project.group.group_project;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class ApplicationD4Testing {

    @Test
    public void verify_Booking_notNull(){
        String id = null;
        String homeOwnerId = "homeOwner";
        String serviceId = null;
        String serviceProviderId = null;
        int startHour = 6;
        int startMinute = 14;
        int endHour = 9;
        int endMinute = 36;
        int day = 3;
        int month = 12;
        int year = 2018;

        Booking booking = new Booking(id, homeOwnerId, serviceId, serviceProviderId, startHour, startMinute, endHour, endMinute, day, month, year);

        assertNotNull(booking);
    }

    @Test
    public void verify_Rating_NotNull(){
        String id = null;
        String serviceId = null;
        String comment = "This service was great";
        int rate = 4;

        Rating rating = new Rating(id, serviceId, comment, rate);

        assertNotNull(rating);
    }

    @Test
    public void verify_HomeOwner_IsValid(){
        String id = "-K212KS0J_1K2K9S-A92J3";
        String username = "homeOwner";
        String password = "homeOwner";
        UserRole role = UserRole.getRoleByName("HOME_OWNER");

        HomeOwner homeOwner = new HomeOwner(id, username, password, role);

        boolean result = HomeOwner.isValidHomeOwner(homeOwner);

        assertEquals(true, result);
    }

    @Test
    public void verify_HomeOwner_NotNull(){
        String id = "ho";
        String username = "ho";
        String password = "ho";
        UserRole role = UserRole.getRoleByName("HOME_OWNER");

        HomeOwner homeOwner = new HomeOwner(id, username, password, role);

        assertNotNull(homeOwner);
    }

    @Test
    public void verify_Rating_IsValid(){
        boolean isValid;
        String serviceComment = "Service was awesome";
        String serviceRate = "8";
        if (!Util.validateInteger(serviceRate)) {
            isValid = false;
        }

        int rateInteger = Integer.parseInt(serviceRate);

        if (serviceComment == null || serviceComment.isEmpty()){
            isValid = false;
        }

        if (serviceRate == null || serviceRate.isEmpty()){
            isValid = false;
        }

        if (rateInteger <= 0 || rateInteger > 5){
            isValid = false;
        }
        else {
            isValid = true;
        }

        assertEquals(false, isValid);
    }

    @Test
    public void verify_Date_HasntPassedFunction(){
        int day = 25;
        int month = 11;
        int year = 2018;

        boolean result = Util.validateDateHasNotPassed(day, month, year);

        assertEquals(true, result);
    }

    @Test
    public void verify_Day_Function() {
        int day = 28;
        int month = 14;
        int year = 2020;

        boolean result = Util.validateDay(day, month, year);

        assertNotEquals(true, result);
    }

    @Test
    public void verify_BookingOverlap(){
        String id = null;
        String homeOwnerId = "homeOwner";
        String serviceId = null;
        String serviceProviderId = null;
        int startHour = 6;
        int startMinute = 14;
        int endHour = 9;
        int endMinute = 36;
        int day = 3;
        int month = 12;
        int year = 2018;
        int startHour1 = 5;
        int startMinute1 = 59;
        int endHour1 = 6;
        int endMinute1 = 15;

        Booking booking = new Booking(id, homeOwnerId, serviceId, serviceProviderId, startHour, startMinute, endHour, endMinute, day, month, year);
        Booking booking2 = new Booking(id, homeOwnerId, serviceId, serviceProviderId, startHour1, startMinute1, endHour1, endMinute1, day, month, year);

        boolean result = false;
        if (booking.getDay() == booking2.getDay() && booking.getMonth() == booking2.getMonth() && booking.getYear() == booking2.getYear()){
            if (booking.getStartHour() < booking2.getStartHour()){
                if (booking.getEndHour() > booking2.getEndHour()){
                    result = true;
                } else if (booking.getEndHour() < booking2.getEndHour()){
                    if (booking.getEndHour() < booking2.getStartHour()){
                        result = true;
                    } else {
                        result = false;
                    }
                } else if (booking.getEndHour() == booking2.getEndHour()){
                    result = true;
                }
            } else if (booking.getStartHour() > booking2.getStartHour()){
                if (booking.getEndHour() > booking2.getEndHour()){
                    if (booking2.getEndHour() >= booking.getStartHour()){
                        result = true;
                    } else {
                        result = false;
                    }
                } else if (booking.getEndHour() < booking2.getEndHour()){
                    if (booking.getEndHour() < booking2.getStartHour()){
                        result = true;
                    } else {
                        result = false;
                    }
                } else if (booking.getEndHour() == booking2.getEndHour()){
                    result = true;
                }
            } else if (booking.getStartHour() == booking2.getStartHour()){
                result = true;
            }
        } else {
            result = false;
        }

        assertEquals(true, result);
    }


    @Test
    public void verify_StartIsBeforeEnd_Function(){
        int startHour = 22;
        int startMinute = 23;
        int endHour = 22;
        int endMinute = 18;

        boolean result = Util.startIsBeforeEnd(startHour, startMinute, endHour, endMinute);

        assertEquals(false, result);
    }

    @Test
    public void verify_Booking_IsValid(){
        String id = null;
        String homeOwnerId = "homeOwner";
        String serviceId = null;
        String serviceProviderId = null;
        int startHour = 6;
        int startMinute = 14;
        int endHour = 9;
        int endMinute = 36;
        int day = 3;
        int month = 12;
        int year = 2018;

        Booking booking = new Booking(id, homeOwnerId, serviceId, serviceProviderId, startHour, startMinute, endHour, endMinute, day, month, year);

        boolean result = false;
        if (booking.getId() != null && booking.getHomeOwnerId() != null && booking.getServiceId() != null && booking.getServiceProviderId() != null){
            if (booking.getStartHour() < booking.getEndHour() && booking.getStartHour() >= 0 && booking.getStartHour() <= 23){
                if (booking.getEndHour() >= 0 && booking.getEndHour() <= 59){
                    boolean isDayValid = Util.validateDay(day, month, year);
                    if (isDayValid){
                        result = true;
                    }
                }
            }
        }

        assertEquals(false, result);
    }




}
