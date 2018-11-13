package com.project.group.group_project;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileDatabase {

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference databaseProfiles = database.getReference("profiles");

    private static final ProfileDatabase instance = new ProfileDatabase();

    private static List<Profile> profiles = new ArrayList<Profile>();

    static {

        databaseProfiles.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                profiles.clear();

                for (DataSnapshot ds: dataSnapshot.getChildren()) {

                    String address = ds.child("address").getValue(String.class);
                    String phoneNum = ds.child("phoneNum").getValue(String.class);
                    String companyName = ds.child("companyName").getValue(String.class);
                    String description = ds.child("description").getValue(String.class);
                    String licensed = ds.child("licensed").getValue(String.class);
                    String id = ds.child("id").getValue(String.class);
                    String avail = ds.child("availability").getValue(String.class);

                    Profile profile = new Profile(id, description, address, phoneNum, companyName, licensed, avail);

                    profiles.add(profile);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private ProfileDatabase() {

    }

    public static ProfileDatabase getInstance() {
        return instance;
    }

    public boolean isProfileAlreadyInDatabase(String name) {

        for (Profile profile: profiles) {

            if (profile.getCompanyName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public Profile addProfile(String description, String address, String phoneNum, String companyName, String licensed, String avail) {

        String id = databaseProfiles.push().getKey();

        Profile profile = new Profile(id, description, address, phoneNum, companyName, licensed, avail);

        databaseProfiles.child(id).setValue(profile);

        return profile;
    }

    public void updateProfile(String originalName, String username, String description, String address, String phoneNum, String companyName, String licensed, String avail) {

        Profile profile = getProfile(originalName);

        DatabaseReference tmpRef = databaseProfiles.child(profile.getProfId());

        tmpRef.child("companyName").setValue(companyName);
        tmpRef.child("description").setValue(description);
        tmpRef.child("address").setValue(address);
        tmpRef.child("phoneNum").setValue(phoneNum);
        tmpRef.child("licensed").setValue(licensed);
        tmpRef.child("availability").setValue(avail);
    }

    public Profile getProfile(String companyName) {

        for (Profile profile: profiles) {
            if (profile.getCompanyName().equals(companyName)) {
                return profile;
            }
        }

        return null;
    }

    public boolean deleteProfile(Profile profile) {

        DatabaseReference tmpRef = databaseProfiles.child(profile.getProfId());

        tmpRef.removeValue();

        return true;
    }

    public List<Profile> getProfiles() {

        return profiles;
    }

}
