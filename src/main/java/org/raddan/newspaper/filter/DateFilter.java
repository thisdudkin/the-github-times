package org.raddan.newspaper.filter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFilter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy - hh:mm:ss a", Locale.ENGLISH);

    public static String formatInstant(long epochSecond) {
        return LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.UTC).format(formatter) + " - (UTC)";
    }

}
