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
    
    //  Arrays
    private final ArrayList<String> sentMessagesArray;
    private final ArrayList<String> disregardedMessagesArray;
    private final ArrayList<String> storedMessagesArray;
    private final ArrayList<String> messageHashArray;
    private final ArrayList<String> messageIDArray;
    
    public Message(int maxMessages) {
        this.messages = new ArrayList<>();
        this.totalMessagesSent = 0;
        this.maxMessages = maxMessages;
        
        // Initializing arrays
        this.sentMessagesArray = new ArrayList<>();
        this.disregardedMessagesArray = new ArrayList<>();
        this.storedMessagesArray = new ArrayList<>();
        this.messageHashArray = new ArrayList<>();
        this.messageIDArray = new ArrayList<>();
    }
    
    private class MessageObject {
        String messageID;
        int messageNumber;
        String recipient;
        String messageText;
        String messageHash;
        String status;
        
        MessageObject(String messageID, int messageNumber, String recipient, 
                      String messageText, String messageHash, String status) {
            this.messageID = messageID;
            this.messageNumber = messageNumber;
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
        
        // Adding to ID and Hash arrays 
        messageIDArray.add(messageID);
        messageHashArray.add(messageHash);
        
        switch (choice) {
            case 1:
                status = "sent";
                returnMessage = "Message successfully sent.";
                totalMessagesSent++;
                sentMessagesArray.add(messageText);
                break;
            case 2:
                status = "disregarded";
                returnMessage = "Press 0 to delete the message.";
                disregardedMessagesArray.add(messageText);
                break;
            case 3:
                status = "stored";
                returnMessage = "Message successfully stored.";
                storedMessagesArray.add(messageText);
                break;
            default:
                return "Invalid option selected.";
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
    
    //METHODS
    
    public String displayStoredMessages() {
        if (storedMessagesArray.isEmpty()) {
            return "No stored messages found.";
        }
        
        StringBuilder output = new StringBuilder();
        output.append("\n--- STORED MESSAGES ---\n");
        for (int i = 0; i < storedMessagesArray.size(); i++) {
            output.append((i + 1) + ". Message: " + storedMessagesArray.get(i) + "\n");
        }
        return output.toString();
    }
    
    public String findLongestMessage() {
        if (storedMessagesArray.isEmpty()) {
            return "No stored messages to check.";
        }
        
        String longest = storedMessagesArray.get(0);
        for (String msg : storedMessagesArray) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }
    
    public String searchByMessageID(String searchID) {
        for (int i = 0; i < messageIDArray.size(); i++) {
            if (messageIDArray.get(i).equals(searchID)) {
                return "Message found: " + messages.get(i).messageText;
            }
        }
        return "Message ID not found.";
    }
    
    public String searchByRecipient(String recipientNumber) {
        StringBuilder results = new StringBuilder();
        boolean found = false;
        
        for (MessageObject msg : messages) {
            if (msg.recipient.equals(recipientNumber)) {
                results.append("Message: ").append(msg.messageText).append("\n");
                found = true;
            }
        }
        
        if (found) {
            return results.toString();
        }
        return "No messages found for recipient: " + recipientNumber;
    }
    
    public String deleteByHash(String hashToDelete) {
        for (int i = 0; i < messageHashArray.size(); i++) {
            if (messageHashArray.get(i).equals(hashToDelete)) {
                messageHashArray.remove(i);
                messageIDArray.remove(i);
                messages.remove(i);
                return "Message with hash " + hashToDelete + " successfully deleted.";
            }
        }
        return "Message hash not found.";
    }
    
    public String displayReport() {
        if (messages.isEmpty()) {
            return "No messages to display in report.";
        }
        
        StringBuilder report = new StringBuilder();
        report.append("\n========== MESSAGE REPORT ==========\n");
        
        for (MessageObject msg : messages) {
            report.append("Message Hash: ").append(msg.messageHash).append("\n");
            report.append("Recipient: ").append(msg.recipient).append("\n");
            report.append("Message: ").append(msg.messageText).append("\n");
            report.append("Status: ").append(msg.status).append("\n");
            report.append("------------------------------------\n");
        }
        
        return report.toString();
    }
    
    //  GETTERS FOR TESTING 
    
    public ArrayList<String> getSentMessagesArray() { 
        return sentMessagesArray; 
    }
    
    public ArrayList<String> getStoredMessagesArray() { 
        return storedMessagesArray; 
    }
    
    public ArrayList<String> getMessageHashArray() { 
        return messageHashArray; 
    }
    
    public ArrayList<String> getMessageIDArray() { 
        return messageIDArray; 
    }
}