package edu.wgu.d387_sample_code;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TimeZoneConverter provides utility methods to convert a given time between
 * Eastern Time (ET), Mountain Time (MT), and Coordinated Universal Time (UTC).
 *
 * Per the rubric requirement (B3a), this class supports converting times between
 * these three time zones to display the time of an online live presentation
 * held at the Landon Hotel.
 */
public class TimeZoneConverter {

    // Time zone identifiers used for conversion
    public static final ZoneId EASTERN_TIME = ZoneId.of("America/New_York");
    public static final ZoneId MOUNTAIN_TIME = ZoneId.of("America/Denver");
    public static final ZoneId UTC = ZoneId.of("UTC");

    // Standard formatter for displaying hours and minutes (e.g., "14:30")
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Converts a given ZonedDateTime to Eastern Time and returns it formatted as "HH:mm ET".
     *
     * @param dateTime the original ZonedDateTime to convert
     * @return formatted string showing the time in Eastern Time
     */
    public static String toEasternTime(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(EASTERN_TIME).format(TIME_FORMAT) + " ET";
    }

    /**
     * Converts a given ZonedDateTime to Mountain Time and returns it formatted as "HH:mm MT".
     *
     * @param dateTime the original ZonedDateTime to convert
     * @return formatted string showing the time in Mountain Time
     */
    public static String toMountainTime(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(MOUNTAIN_TIME).format(TIME_FORMAT) + " MT";
    }

    /**
     * Converts a given ZonedDateTime to Coordinated Universal Time and returns it formatted as "HH:mm UTC".
     *
     * @param dateTime the original ZonedDateTime to convert
     * @return formatted string showing the time in UTC
     */
    public static String toUTC(ZonedDateTime dateTime) {
        return dateTime.withZoneSameInstant(UTC).format(TIME_FORMAT) + " UTC";
    }

    /**
     * Convenience method that returns the time in all three zones, separated by " | ".
     * Example output: "18:00 ET | 16:00 MT | 22:00 UTC"
     *
     * @param dateTime the original ZonedDateTime to convert
     * @return formatted string showing the time in all three zones
     */
    public static String displayInAllZones(ZonedDateTime dateTime) {
        return toEasternTime(dateTime) + " | " + toMountainTime(dateTime) + " | " + toUTC(dateTime);
    }
}