/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp_part2_poe;

import org.junit.Test;
import org.junit.Before;

public class MessagePart3Test {
    
    private static Message message;
    
    @Before
    public void setUp() {
        message = new Message(10);
        
        // Test Data Message 1 - Sent
        message.sendMessageOption(1, "ID001", 0, "+27834557896", "Did you get the cake?", "HASH001");
        
        // Test Data Message 2 - Stored (LONGEST message)
        message.sendMessageOption(3, "ID002", 1, "+27838884567", "Where are you? You are late! I have asked you to be on time.", "HASH002");
        
        // Test Data Message 3 - Disregard
        message.sendMessageOption(2, "ID003", 2, "+27834484567", "Yohoooo, I am at your gate.", "HASH003");
        
        // Test Data Message 4 - Sent
        message.sendMessageOption(1, "ID004", 3, "0838884567", "It is dinner time!", "HASH004");
        
        // Test Data Message 5 - Stored
        message.sendMessageOption(3, "ID005", 4, "+27838884567", "Ok, I am leaving without you.", "HASH005");
    }
    
    @Test
    public void testSentMessagesArray() {
        System.out.println("\nTest 1: Sent Messages Array");
        boolean pass1 = message.getSentMessagesArray().contains("Did you get the cake?");
        boolean pass2 = message.getSentMessagesArray().contains("It is dinner time!");
        
        if (pass1) {
            System.out.println("✓ Pass: Found 'Did you get the cake?'");
        } else {
            System.out.println("✗ Fail: 'Did you get the cake?' not found");
        }
        
        if (pass2) {
            System.out.println("✓ Pass: Found 'It is dinner time!'");
        } else {
            System.out.println("✗ Fail: 'It is dinner time!' not found");
        }
    }
    
    @Test
    public void testLongestMessage() {
        System.out.println("\nTest 2: Longest Message");
        String longest = message.findLongestMessage();
        System.out.println("Longest message: " + longest);
        
        String expected = "Where are you? You are late! I have asked you to be on time.";
        if (longest.equals(expected)) {
            System.out.println("✓ Pass");
        } else {
            System.out.println("✗ Fail - Expected: " + expected);
        }
    }
    
    @Test
    public void testSearchByMessageID() {
        System.out.println("\nTest 3: Search by Message ID");
        String result = message.searchByMessageID("ID004");
        System.out.println("Result: " + result);
        
        if (result.contains("It is dinner time!")) {
            System.out.println("✓ Pass");
        } else {
            System.out.println("✗ Fail");
        }
    }
    
    @Test
    public void testSearchByRecipient() {
        System.out.println("\nTest 4: Search by Recipient");
        String result = message.searchByRecipient("+27838884567");
        System.out.println("Result: " + result);
        
        if (result.contains("Where are you?") && result.contains("Ok, I am leaving without you.")) {
            System.out.println("✓ Pass");
        } else {
            System.out.println("✗ Fail");
        }
    }
    
    @Test
    public void testDeleteByHash() {
        System.out.println("\nTest 5: Delete by Hash");
        String result = message.deleteByHash("HASH002");
        System.out.println("Result: " + result);
        
        if (result.contains("successfully deleted")) {
            System.out.println("✓ Pass");
        } else {
            System.out.println("✗ Fail");
        }
    }
    
    @Test
    public void testDisplayReport() {
        System.out.println("\nTest 6: Display Report");
        String report = message.displayReport();
        System.out.println(report);
        
        if (report.contains("Message Hash") && report.contains("Recipient") && report.contains("Message")) {
            System.out.println("✓ Pass - Report contains required headers");
        } else {
            System.out.println("✗ Fail - Report missing required headers");
        }
    }
    
    // Main method to run all tests
    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("RUNNING PART 3 TESTS");
        System.out.println("=".repeat(50));
        
        MessagePart3Test test = new MessagePart3Test();
        test.setUp();
        
        test.testSentMessagesArray();
        test.testLongestMessage();
        test.testSearchByMessageID();
        test.testSearchByRecipient();
        test.testDeleteByHash();
        test.testDisplayReport();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ALL TESTS COMPLETE");
        System.out.println("=".repeat(50));
    }
}

