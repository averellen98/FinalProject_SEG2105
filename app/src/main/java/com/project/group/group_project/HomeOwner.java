package com.project.group.group_project;

public class HomeOwner extends User {

    public HomeOwner(String id, String username, String password, UserRole role) {
        super(id, username, password, role);
    }

    public static boolean isValidHomeOwner(HomeOwner homeOwner){
        if (homeOwner.getId() != "" && homeOwner.getUsername() != "" && homeOwner.getPassword() != "" && homeOwner.getFirstName() != "" && homeOwner.getLastName() != ""){
            return true;
        }
        else {
            return false;
        }
    }

}
