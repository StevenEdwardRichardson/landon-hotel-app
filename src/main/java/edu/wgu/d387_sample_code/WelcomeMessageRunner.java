package edu.wgu.d387_sample_code;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * WelcomeMessageRunner displays a localized welcome message at application startup.
 *
 * Implements CommandLineRunner so Spring Boot automatically executes the run() method
 * once the application context is fully initialized.
 *
 * Per the rubric requirement (B1b), the welcome message is displayed in both English
 * and French using a different thread for each language. Each thread loads its own
 * ResourceBundle with the appropriate Locale and prints the welcome.message key.
 */
@Component
public class WelcomeMessageRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {

        // Thread 1: Load the English resource bundle and display the welcome message
        Thread englishThread = new Thread(() -> {
            ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);
            System.out.println("English: " + bundle.getString("welcome.message"));
        });

        // Thread 2: Load the French resource bundle and display the welcome message
        Thread frenchThread = new Thread(() -> {
            ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.FRENCH);
            System.out.println("French:  " + bundle.getString("welcome.message"));
        });

        // Start both threads
        englishThread.start();
        frenchThread.start();

        // Wait for both threads to complete before continuing
        try {
            englishThread.join();
            frenchThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Welcome message thread was interrupted: " + e.getMessage());
        }
    }
}