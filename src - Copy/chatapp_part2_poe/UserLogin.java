/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp_part2_poe;

/**
 * Registration and Login System for Chat App
 * References:
 * - Regular expression for phone number: https://www.regexplanet.com/
 * - Password complexity pattern: Based on OWASP password guidelines
 * 
 * @author Student
 */

public class UserLogin {

    private String storedUsername;
    private String storedPassword;
    private String storedPhoneNumber;
    private String storedFirstName;
    private String storedLastName;

    // Method 1: Check username format (contains underscore, max 5 chars)
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Method 2: Check password complexity
    public boolean checkPasswordComplexity(String password) {
        boolean hasLength = password.length() >= 8;
        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
        
        return hasLength && hasCapital && hasNumber && hasSpecial;
    }

    // Method 3: Check cell phone number (must have international code +27 or 0027)
    public boolean checkCellPhoneNumber(String phoneNumber) {
        // Accepts: +27 followed by 9 digits OR 0027 followed by 9 digits
        return phoneNumber.matches("\\+27\\d{9}") || phoneNumber.matches("0027\\d{9}");
    }

    // Method 4: Register user - returns appropriate message
    public String registerUser(String username, String password, String phoneNumber, 
                               String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username " +
                   "contains an underscore and is no more than five characters in length.";
        }
        else if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password " +
                   "contains at least eight characters, a capital letter, a number, and a special character.";
        }
        else if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        else {
            storedUsername = username;
            storedPassword = password;
            storedPhoneNumber = phoneNumber;
            storedFirstName = firstName;
            storedLastName = lastName;
            return "Cell phone number successfully added.";
        }
    }

    // Method 5: Login verification
    public boolean loginUser(String username, String password) {
        if (storedUsername == null) {
            return false;
        }
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    // Method 6: Return login status message
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + storedFirstName + ", " + storedLastName + 
                   " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    // Getter methods for testing
    public String getStoredUsername() { return storedUsername; }
    public String getStoredPassword() { return storedPassword; }
    public String getStoredPhoneNumber() { return storedPhoneNumber; }
}