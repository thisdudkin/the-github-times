package org.raddan.newspaper.filter;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Lazy
@Component
public class DateFilter {

    @Lazy
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy - hh:mm:ss a", Locale.ENGLISH);

    @Lazy
    public String formatInstant(long epochSecond) {
        return LocalDateTime.ofEpochSecond(epochSecond, 0, ZoneOffset.UTC).format(formatter) + " - (UTC)";
    }

}
