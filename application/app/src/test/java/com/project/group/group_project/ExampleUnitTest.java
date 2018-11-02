package com.project.group.group_project;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void verifyUserGetsAddedToDatabaseWhenCreated() {
        String email = "test@email";
        String password = "test";
        String role = "TEST_ROLE";

        UserDatabase userDatabase = new UserDatabase();

        userDatabase.addUser(email, password, role, null, null);

        assertNotNull(userDatabase.getUsers());
    }

    @Test
    public void verifyServiceDatabaseIsNotNull() {
        ServiceDatabase db = new ServiceDatabase();

        List<Service> services = db.getServices();

        assertNotNull(services);
    }
}