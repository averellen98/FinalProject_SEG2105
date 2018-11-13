package com.project.group.group_project;

import java.util.ArrayList;
import java.util.List;

public class Profile {

    private String username;
    private String address;
    private String phoneNum;
    private String companyName;
    private String description;
    private String licensed;
    private String id;
    private String availability;
    private List<Service> servicesAvail = new ArrayList<Service>();;

    /**
     * We represent money as an integer, the last two digits will be the decimal value.
     * Therefore 1$ is 100.
     */

    public Profile(String id, String description, String address, String phoneNum, String companyName, String licensed, String availability) {
        this.username = username;
        this.id = id;
        this.description = description;
        this.address = address;
        this.phoneNum = phoneNum;
        this.licensed = licensed;
        this.companyName = companyName;
        this.availability = availability;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProfDescription() {
        return description;
    }

    public void setProfDescription(String description) {
        this.description = description;
    }

    public String getProfAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfId() {
        return id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public String getLicensed() { return licensed; }

    public void setLicensed(String licensed) { this.licensed = licensed; }

    public void addServices(Service service){
        servicesAvail.add(service);
    }

    public List<Service> getServicesAvail(){
        return servicesAvail;
    }

    public String getAvailability(){ return availability; }

    public void setAvailability(String newAvailability) { this.availability = newAvailability; }

    public boolean isValidProfile(Profile profile){
        if (profile.getPhoneNum() != "" && profile.getProfId() != "" && profile.getProfAddress() != "" && profile.getProfDescription() != "" && profile.getCompanyName() != ""){
            return true;
        }
        else {
            return false;
        }
    }

}
