package com.project.group.group_project;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void verifyUserCriteria_isCorrect() {
    	SignUpActivity tester = new SignUpActivity();
        assertFalse(tester.SignUpActivity(email, password1, password), false);
        
    }
    
    public void checksToSeeifUserExists() {
    	SignUpActivity tester = new SignUpActivity();
    	assertnotNull(tester.createuser(email, password, role, "John", "Smith"), user )
    }
}