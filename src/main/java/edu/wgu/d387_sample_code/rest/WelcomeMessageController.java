package edu.wgu.d387_sample_code.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.ResourceBundle;

@RestController
@RequestMapping(ResourceConstants.WELCOME_MESSAGE_V1)
@CrossOrigin
public class WelcomeMessageController {

    @GetMapping
    public ResponseEntity<String> getWelcomeMessages() {
        ResourceBundle englishBundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);
        ResourceBundle frenchBundle = ResourceBundle.getBundle("messages", Locale.FRENCH);

        String englishMessage = englishBundle.getString("welcome.message");
        String frenchMessage = frenchBundle.getString("welcome.message");

        String response = "English: " + englishMessage + " | French: " + frenchMessage;

        return ResponseEntity.ok(response);
    }
}