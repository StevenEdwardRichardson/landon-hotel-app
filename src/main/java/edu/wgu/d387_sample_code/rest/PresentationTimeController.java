package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.TimeZoneConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * REST controller that exposes the time of an online live presentation
 * held at the Landon Hotel, displayed in three time zones (ET, MT, UTC).
 *
 * Per the rubric requirement (B3b), the time is shown in hours and minutes
 * across all three zones using the conversion method from B3a (TimeZoneConverter).
 *
 * The presentation is fixed at 6:00 PM Eastern Time on November 15, 2026,
 * which is a typical hotel evening event time.
 */
@RestController
@RequestMapping(ResourceConstants.PRESENTATION_TIME_V1)
@CrossOrigin
public class PresentationTimeController {

    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPresentationTime() {

        // Define the presentation time: 6:00 PM Eastern Time on November 15, 2026
        ZonedDateTime presentationTime = ZonedDateTime.of(
                2026, 11, 15, 18, 0, 0, 0,
                ZoneId.of("America/New_York")
        );

        // Use the TimeZoneConverter (B3a) to format the time in all three zones
        String formattedTime = TimeZoneConverter.displayInAllZones(presentationTime);

        // Build a complete display message
        String message = "Online Live Presentation: " + formattedTime;

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}