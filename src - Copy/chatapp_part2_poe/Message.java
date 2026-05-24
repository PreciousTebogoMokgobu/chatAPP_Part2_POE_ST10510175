/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp_part2_poe;

import java.util.ArrayList;
import java.util.Random;

public class Message {
    
    private final ArrayList<MessageObject> messages;
    private int totalMessagesSent;
    private final int maxMessages;
    
    public Message(int maxMessages) {
        this.messages = new ArrayList<>();
        this.totalMessagesSent = 0;
        this.maxMessages = maxMessages;
    }
    
    private class MessageObject {
        String messageID;
        String recipient;
        String messageText;
        String messageHash;
        String status;
        
        MessageObject(String messageID, int messageNumber, String recipient, 
                      String messageText, String messageHash, String status) {
            this.messageID = messageID;
            this.recipient = recipient;
            this.messageText = messageText;
            this.messageHash = messageHash;
            this.status = status;
        }
    }
    
    public boolean checkMessageID(String messageID) {
        return messageID != null && messageID.length() <= 10;
    }
    
    public String checkRecipientCell(String recipientNumber) {
        if (recipientNumber.matches("\\+27\\d{9}") || recipientNumber.matches("0027\\d{9}")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    public String createMessageHash(String messageID, int messageNumber, String messageText) {
        String firstTwoNumbers = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        return firstTwoNumbers + ":" + messageNumber + ":" + firstWord + lastWord;
    }
    
    public String generateMessageID() {
        Random rand = new Random();
        long tenDigitNumber = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(tenDigitNumber);
    }
    
    public String checkMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }
    
    public String sendMessageOption(int choice, String messageID, int messageNumber, 
                                     String recipient, String messageText, String messageHash) {
        String status;
        String returnMessage;
        
        switch (choice) {
            case 1 -> {
                status = "sent";
                returnMessage = "Message successfully sent.";
                totalMessagesSent++;
            }
            case 2 -> {
                status = "disregarded";
                returnMessage = "Press 0 to delete the message.";
            }
            case 3 -> {
                status = "stored";
                returnMessage = "Message successfully stored.";
            }
            default -> {
                return "Invalid option selected.";
            }
        }
        
        MessageObject msg = new MessageObject(messageID, messageNumber, recipient, 
                                               messageText, messageHash, status);
        messages.add(msg);
        
        return returnMessage;
    }
    
    public String printMessages() {
        if (messages.isEmpty()) {
            return "No messages to display.";
        }
        
        StringBuilder output = new StringBuilder();
        output.append("\n============================================\n");
        output.append("MESSAGES\n");
        output.append("============================================\n");
        
        for (MessageObject msg : messages) {
            output.append("Message ID: ").append(msg.messageID).append("\n");
            output.append("Message Hash: ").append(msg.messageHash).append("\n");
            output.append("Recipient: ").append(msg.recipient).append("\n");
            output.append("Message: ").append(msg.messageText).append("\n");
            output.append("Status: ").append(msg.status).append("\n");
            output.append("----------------------------------------\n");
        }
        
        return output.toString();
    }
    
    public int returnTotalMessages() {
        return totalMessagesSent;
    }
    
    public int getMaxMessages() {
        return maxMessages;
    }
    
    public boolean canSendMoreMessages() {
        return messages.size() < maxMessages;
    }
    
    public int getCurrentMessageCount() {
        return messages.size();
    }
}