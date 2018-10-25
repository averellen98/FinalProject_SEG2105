package com.project.group.group_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private TextView email;
    private TextView password;
    private TextView confirmPassword;
    private TextView firstName;
    private TextView lastName;
    private RadioGroup roleGroup;

    public static List<User> users;
    public static ListView usersListView;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public boolean isThere = false;

    int x = 0;

    public static List<User> getUsers(){
        return users;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        confirmPassword = findViewById(R.id.passwordConfirmText);
        firstName = findViewById(R.id.firstNameText);
        lastName = findViewById(R.id.lastNameText);
        roleGroup = findViewById(R.id.roleRadioGroup);
        users = new ArrayList<>();
    }

    public void signUpOnClick(View view) {

        final String u_email = email.getText().toString();
        String u_password = password.getText().toString();
        String u_confirmPassword = confirmPassword.getText().toString();
        String role = getRoleFromRadioButtonId(roleGroup.getCheckedRadioButtonId());
        String u_firstName = firstName.getText().toString();
        String u_lastName = lastName.getText().toString();

        boolean canBeUser = verifyUserCriteria(u_email, u_password, u_confirmPassword);

        if (canBeUser) {

            //want to check if the user is already a user, and if not then add it

            if (role.equals("ADMIN")){
                if (u_email.equals("admin") && u_password.equals("admin")){
                    //x++;
                    User addUser = createUser(u_email, u_password, role, u_firstName, u_lastName);

                    // implement creating user to the database
                    DatabaseReference dU = database.getReference();
                    DatabaseReference db = dU.child("users");
                    String id = db.push().getKey();
                    DatabaseReference dataSave = db.child(id);
                    dataSave.setValue(addUser);
                    users.add(addUser);

                    //start the WelcomeActivity.class
                    Intent intent = new Intent(this, WelcomeActivity.class);
                    intent.putExtra(MainActivity.USER_ROLE_KEY, role.toString());
                    intent.putExtra(MainActivity.USER_FIRSTNAME_KEY, u_firstName);
                    startActivity(intent);
                    setContentView(R.layout.activity_welcome);
                } else {
                    Toast.makeText(this, "Admin user already exists, please try again.", Toast.LENGTH_SHORT).show();
                    email.setText("");
                    password.setText("");
                    confirmPassword.setText("");
                    firstName.setText("");
                    lastName.setText("");
                }
            } else {
                User addUser = createUser(u_email, u_password, role, u_firstName, u_lastName);

                // implement creating user to the database
                DatabaseReference dU = database.getReference();
                DatabaseReference db = dU.child("users");

                String id = db.push().getKey();
                DatabaseReference dataSave = db.child(id);
                dataSave.setValue(addUser);
                users.add(addUser);

                //start the WelcomeActivity.class
                Intent intent = new Intent(this, WelcomeActivity.class);
                intent.putExtra(MainActivity.USER_ROLE_KEY, role.toString());
                intent.putExtra(MainActivity.USER_FIRSTNAME_KEY, u_firstName);
                startActivity(intent);
                setContentView(R.layout.activity_welcome);
            }


        } else {
            Toast.makeText(this, "Please input all required information.", Toast.LENGTH_LONG).show();
        }
    }

    private String getRoleFromRadioButtonId(int id) {

        RadioButton rb = findViewById(id);

        return rb.getText().toString();
    }

    private boolean verifyUserCriteria(String email, String password, String confirmPassword) {

        if (email == null || email.isEmpty()) {
            return false;
        }

        if (password == null || password.isEmpty()) {
            return false;
        }

        if (confirmPassword == null || confirmPassword.isEmpty()) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            return false;
        }

        return true;
    }

    private User createUser(String email, String password, String role, String firstName, String lastName) {

        UserRole userRole = UserRole.getRoleByName(role);

        User user = null;

        switch (userRole) {
            case ADMIN:
                user = new Admin(email, password, userRole);
                break;
            case HOME_OWNER:
                user = new HomeOwner(email, password, userRole);
                break;
            case SERVICE_PROVIDER:
                user = new ServiceProvider(email, password, userRole);
                break;
        }

        if (firstName != null && !firstName.isEmpty()) {
            user.setFirstName(firstName);
        }

        if (lastName != null && !lastName.isEmpty()) {
            user.setLastName(lastName);
        }

        return user;
    }
}
