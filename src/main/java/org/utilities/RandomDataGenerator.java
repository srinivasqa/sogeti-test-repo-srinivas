package org.utilities;

import java.util.Random;

public class RandomDataGenerator {

    private static final String[] FIRST_NAMES = {
            "John", "Jane", "Alex", "Emily", "Chris", "Katie", "Michael", "Sarah", "David", "Laura"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Brown", "Williams", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"
    };

    public static final String DOMAIN = "@mailinator.com";

    public static String getRandomFirstName() {
        Random random = new Random();
        int index = random.nextInt(FIRST_NAMES.length);
        return FIRST_NAMES[index];
    }

    public static String getRandomLastName() {
        Random random = new Random();
        int index = random.nextInt(LAST_NAMES.length);
        return LAST_NAMES[index];
    }

    public static String generateRandomEmail(String firstName, String lastName) {
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + DOMAIN;
    }

    public static String generateRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder("+49");
        for (int i = 0; i < 10; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }

    public static String generateRandomMessage() {
        String[] words = {"Hello", "this", "is", "a", "random", "message", "for", "testing", "purposes"};
        Random random = new Random();
        int wordCount = 5 + random.nextInt(6); // Random message length between 5 and 10 words
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < wordCount; i++) {
            message.append(words[random.nextInt(words.length)]).append(" ");
        }
        return message.toString().trim() + ".";
    }

}
