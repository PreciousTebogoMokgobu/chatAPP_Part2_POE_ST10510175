/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp_part2_poe;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class UserLoginTest {

    private UserLogin user;
    
    @Before
    public void setUp() {
        user = new UserLogin();
    }

    @Test
    public void testUsernameCorrectlyFormatted_AssertEquals() {
        String result = user.registerUser("kyl_1", "Ch&&sec@ke99!", "+27831234567", "John", "Doe");
        assertEquals("Cell phone number successfully added.", result);
    }
    
    @Test
    public void testUsernameIncorrectlyFormatted_AssertEquals() {
        String result = user.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27831234567", "John", "Doe");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }
    
    @Test
    public void testPasswordMeetsComplexity_AssertEquals() {
        String result = user.registerUser("kyl_1", "Ch&&sec@ke99!", "+27831234567", "John", "Doe");
        assertEquals("Cell phone number successfully added.", result);
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexity_AssertEquals() {
        String result = user.registerUser("kyl_1", "password", "+27831234567", "John", "Doe");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }
    
    @Test
    public void testCellPhoneCorrectlyFormatted_AssertEquals() {
        String result = user.registerUser("kyl_1", "Ch&&sec@ke99!", "+27831234567", "John", "Doe");
        assertEquals("Cell phone number successfully added.", result);
    }
    
    @Test
    public void testCellPhoneIncorrectlyFormatted_AssertEquals() {
        String result = user.registerUser("kyl_1", "Ch&&sec@ke99!", "0834567890", "John", "Doe");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }

    @Test
    public void testLoginSuccessful_AssertTrue() {
        user.registerUser("kyl_1", "Ch&&sec@ke99!", "+27831234567", "John", "Doe");
        boolean result = user.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(result);
    }
    
    @Test
    public void testLoginFailed_AssertFalse() {
        user.registerUser("kyl_1", "Ch&&sec@ke99!", "+27831234567", "John", "Doe");
        boolean result = user.loginUser("kyl_1", "wrongpassword");
        assertFalse(result);
    }
    
    @Test
    public void testUsernameCorrectlyFormatted_AssertTrue() {
        boolean result = user.checkUserName("kyl_1");
        assertTrue(result);
    }
    
    @Test
    public void testUsernameIncorrectlyFormatted_AssertFalse() {
        boolean result = user.checkUserName("kyle!!!!!!!");
        assertFalse(result);
    }
    
    @Test
    public void testPasswordMeetsComplexity_AssertTrue() {
        boolean result = user.checkPasswordComplexity("Ch&&sec@ke99!");
        assertTrue(result);
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexity_AssertFalse() {
        boolean result = user.checkPasswordComplexity("password");
        assertFalse(result);
    }
    
    @Test
    public void testCellPhoneCorrectlyFormatted_AssertTrue() {
        boolean result = user.checkCellPhoneNumber("+27831234567");
        assertTrue(result);
    }
    
    @Test
    public void testCellPhoneIncorrectlyFormatted_AssertFalse() {
        boolean result = user.checkCellPhoneNumber("0834567890");
        assertFalse(result);
    }
    
    // ===== ADD THIS MAIN METHOD RIGHT HERE =====
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("chatapp_part2_poe.UserLoginTest");
    }
    // ===========================================
}