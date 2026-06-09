/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp_part2_poe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            UserLogin userLogin = new UserLogin();
            
            System.out.println("=".repeat(50));
            System.out.println("WELCOME TO QUICKCHAT");
            System.out.println("=".repeat(50));
            
            boolean registrationComplete = false;
            
            while (!registrationComplete) {
                System.out.print("\nEnter first name: ");
                String firstName = input.nextLine();
                
                System.out.print("Enter last name: ");
                String lastName = input.nextLine();
                
                System.out.print("Enter username (must contain '_' and be ≤ 5 chars): ");
                String username = input.nextLine();
                
                System.out.print("Enter password (8+ chars, 1 capital, 1 number, 1 special char): ");
                String password = input.nextLine();
                
                System.out.print("Enter cell phone number (e.g., +27831234567): ");
                String phoneNumber = input.nextLine();
                
                String registrationResult = userLogin.registerUser(username, password,
                        phoneNumber, firstName, lastName);
                System.out.println("\n" + registrationResult);
                
                if (registrationResult.equals("Cell phone number successfully added.")) {
                    registrationComplete = true;
                    System.out.println("\n✓ Registration complete! Please login.\n");
                } else {
                    System.out.println("\nPlease try again.\n");
                }
            }
            
            System.out.println("=".repeat(50));
            System.out.println("LOGIN");
            System.out.println("=".repeat(50));
            
            boolean loggedIn = false;
            int attempts = 0;
            int maxAttempts = 3;
            
            while (!loggedIn && attempts < maxAttempts) {
                System.out.print("\nEnter username: ");
                String loginUsername = input.nextLine();
                
                System.out.print("Enter password: ");
                String loginPassword = input.nextLine();
                
                boolean loginResult = userLogin.loginUser(loginUsername, loginPassword);
                String loginMessage = userLogin.returnLoginStatus(loginResult);
                
                System.out.println(loginMessage);
                
                if (loginResult) {
                    loggedIn = true;
                    System.out.println("\n✓ You have successfully logged into the Chat App!");
                } else {
                    attempts++;
                    if (attempts < maxAttempts) {
                        System.out.println("Attempts remaining: " + (maxAttempts - attempts));
                    }
                }
            }
            
            if (!loggedIn) {
                System.out.println("\n✗ Too many failed attempts. Please restart the application.");
                input.close();
                return;
            }
            
            System.out.println("\n" + "=".repeat(50));
            System.out.println("Welcome to QuickChat");
            System.out.println("=".repeat(50));
            
            System.out.print("\nHow many messages do you want to send? ");
            int numMessages = input.nextInt();
            input.nextLine();
            
            Message messagingSystem = new Message(numMessages);
            boolean running = true;
            
            while (running && messagingSystem.canSendMoreMessages()) {
                System.out.println("\n----------------------------------------");
                System.out.println("MAIN MENU");
                System.out.println("----------------------------------------");
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages (Coming Soon)");
                System.out.println("3. Quit");
                System.out.println("4. Stored Messages Menu");
                System.out.print("\nEnter your choice: ");
                
                int choice = input.nextInt();
                input.nextLine();
                
                switch (choice) {
                    case 1 -> sendMessage(input, messagingSystem);
                    case 2 -> System.out.println("\nComing Soon.");
                    case 3 -> {
                        running = false;
                        System.out.println("\nThank you for using QuickChat. Goodbye!");
                    }
                    case 4 -> storedMessagesMenu(input, messagingSystem);
                    default -> System.out.println("\nInvalid choice. Please try again.");
                }
            }
            
            System.out.println("\n" + "=".repeat(50));
            System.out.println("FINAL SUMMARY");
            System.out.println("=".repeat(50));
            System.out.println("Total messages sent: " + messagingSystem.returnTotalMessages());
            System.out.println(messagingSystem.printMessages());
        }
    }
    
    private static void sendMessage(Scanner input, Message messagingSystem) {
        System.out.println("\n----------------------------------------");
        System.out.println("SEND MESSAGE (Message " + (messagingSystem.getCurrentMessageCount() + 1) + 
                           " of " + messagingSystem.getMaxMessages() + ")");
        System.out.println("----------------------------------------");
        
        String messageID = messagingSystem.generateMessageID();
        System.out.println("Generated Message ID: " + messageID);
        
        String recipient;
        boolean validRecipient = false;
        do {
            System.out.print("Enter recipient cell number (e.g., +27831234567): ");
            recipient = input.nextLine();
            String recipientCheck = messagingSystem.checkRecipientCell(recipient);
            if (recipientCheck.equals("Cell phone number successfully captured.")) {
                validRecipient = true;
            } else {
                System.out.println(recipientCheck);
            }
        } while (!validRecipient);
        
        String messageText;
        boolean validLength = false;
        do {
            System.out.print("Enter your message (max 250 characters): ");
            messageText = input.nextLine();
            String lengthCheck = messagingSystem.checkMessageLength(messageText);
            if (lengthCheck.equals("Message ready to send.")) {
                validLength = true;
            } else {
                System.out.println(lengthCheck);
            }
        } while (!validLength);
        
        int messageNumber = messagingSystem.getCurrentMessageCount();
        String messageHash = messagingSystem.createMessageHash(messageID, messageNumber, messageText);
        System.out.println("Generated Message Hash: " + messageHash);
        
        System.out.println("\n----------------------------------------");
        System.out.println("MESSAGE PREVIEW");
        System.out.println("----------------------------------------");
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + messageText);
        
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");
        System.out.print("Enter your choice: ");
        
        int action = input.nextInt();
        input.nextLine();
        
        String result = messagingSystem.sendMessageOption(action, messageID, messageNumber, 
                                                           recipient, messageText, messageHash);
        System.out.println("\n" + result);
        
        if (action == 1) {
            System.out.println("\n============================================");
            System.out.println("MESSAGE SENT SUCCESSFULLY");
            System.out.println("============================================");
            System.out.println("Message ID: " + messageID);
            System.out.println("Message Hash: " + messageHash);
            System.out.println("Recipient: " + recipient);
            System.out.println("Message: " + messageText);
        }
    }
    
    private static void storedMessagesMenu(Scanner input, Message messagingSystem) {
        boolean back = false;
        
        while (!back) {
            System.out.println("\n----------------------------------------");
            System.out.println("STORED MESSAGES MENU");
            System.out.println("----------------------------------------");
            System.out.println("1. Display all stored messages");
            System.out.println("2. Find the longest stored message");
            System.out.println("3. Search for a message by ID");
            System.out.println("4. Search for messages by recipient");
            System.out.println("5. Delete a message using message hash");
            System.out.println("6. Display full report");
            System.out.println("7. Back to Main Menu");
            System.out.print("\nEnter your choice: ");
            
            int choice = input.nextInt();
            input.nextLine();
            
            switch (choice) {
                case 1 -> System.out.println(messagingSystem.displayStoredMessages());
                case 2 -> System.out.println("\nLongest Message: " + messagingSystem.findLongestMessage());
                case 3 -> {
                    System.out.print("Enter Message ID to search: ");
                    String searchID = input.nextLine();
                    System.out.println(messagingSystem.searchByMessageID(searchID));
                }
                case 4 -> {
                    System.out.print("Enter recipient number: ");
                    String recipient = input.nextLine();
                    System.out.println(messagingSystem.searchByRecipient(recipient));
                }
                case 5 -> {
                    System.out.print("Enter Message Hash to delete: ");
                    String hash = input.nextLine();
                    System.out.println(messagingSystem.deleteByHash(hash));
                }
                case 6 -> System.out.println(messagingSystem.displayReport());
                case 7 -> {
                    back = true;
                    System.out.println("\nReturning to Main Menu...");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}