/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp_part2_poe;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunAllTests {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("RUNNING ALL TESTS");
        System.out.println("=".repeat(50));
        
        // Run UserLoginTest
        System.out.println("\n--- Running UserLoginTest ---");
        Result userLoginResult = JUnitCore.runClasses(UserLoginTest.class);
        
        for (Failure failure : userLoginResult.getFailures()) {
            System.out.println("FAILED: " + failure.toString());
        }
        
        System.out.println("UserLoginTest - Successful: " + userLoginResult.wasSuccessful());
        System.out.println("Tests run: " + userLoginResult.getRunCount());
        System.out.println("Failures: " + userLoginResult.getFailureCount());
        
        // Run MessageTest
        System.out.println("\n--- Running MessageTest ---");
        Result messageResult = JUnitCore.runClasses(MessageTest.class);
        
        for (Failure failure : messageResult.getFailures()) {
            System.out.println("FAILED: " + failure.toString());
        }
        
        System.out.println("MessageTest - Successful: " + messageResult.wasSuccessful());
        System.out.println("Tests run: " + messageResult.getRunCount());
        System.out.println("Failures: " + messageResult.getFailureCount());
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ALL TESTS COMPLETE");
        System.out.println("=".repeat(50));
    }
}