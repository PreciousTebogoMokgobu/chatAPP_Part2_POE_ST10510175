/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp_part2_poe;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class MessageTest {
    
    private Message message;
    
    @Before
    public void setUp() {
        message = new Message(5);
    }
    
    @Test
    public void testMessageLengthSuccess() {
        String result = message.checkMessageLength("Hi Tebogo, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", result);
    }
    
    @Test
    public void testMessageLengthFailure() {
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMessage.append("a");
        }
        String result = message.checkMessageLength(longMessage.toString());
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", result);
    }
    
    @Test
    public void testRecipientCellSuccess() {
        String result = message.checkRecipientCell("+27664782377");
        assertEquals("Cell phone number successfully captured.", result);
    }
    
    @Test
    public void testRecipientCellFailure() {
        String result = message.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }
    
    @Test
    public void testMessageHashCorrect() {
        String messageID = "0012345678";
        int messageNumber = 0;
        String messageText = "Hi Tebogo, can you join us for dinner tonight?";
        String result = message.createMessageHash(messageID, messageNumber, messageText);
        assertEquals("00:0:HITONIGHT?", result);
    }
    
    @Test
    public void testMessageIDCreated() {
        String messageID = message.generateMessageID();
        assertNotNull(messageID);
        assertEquals(10, messageID.length());
    }
    
    @Test
    public void testSendMessageOption() {
        String result = message.sendMessageOption(1, "0012345678", 0, "+27718693002", 
                                                   "Test message", "00:0:TESTMESSAGE");
        assertEquals("Message successfully sent.", result);
    }
    
    @Test
    public void testDisregardMessageOption() {
        String result = message.sendMessageOption(2, "0012345678", 1, "+27718693002", 
                                                   "Test message", "00:1:TESTMESSAGE");
        assertEquals("Press 0 to delete the message.", result);
    }
    
    @Test
    public void testStoreMessageOption() {
        String result = message.sendMessageOption(3, "0012345678", 2, "+27718693002", 
                                                   "Test message", "00:2:TESTMESSAGE");
        assertEquals("Message successfully stored.", result);
    }
    
    @Test
    public void testReturnTotalMessages() {
        message.sendMessageOption(1, "0012345678", 0, "+27718693002", "Message 1", "00:0:MSG1");
        message.sendMessageOption(1, "0023456789", 1, "+27718693003", "Message 2", "00:1:MSG2");
        assertEquals(2, message.returnTotalMessages());
    }
    
    // ===== ADD THIS MAIN METHOD RIGHT HERE =====
    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("chatapp_part2_poe.MessageTest");
    }
    // ===========================================
}