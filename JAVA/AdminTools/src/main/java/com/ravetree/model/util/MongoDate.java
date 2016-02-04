package com.ravetree.model.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Brent Baker on 6/21/15.
 */
public class MongoDate {

    public static DateTime convertToDateTime(String date) {

        if (date != null) {
            // Formatter for the input date
            DateTimeFormatter fmt = DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss zzz yyyy");
            return fmt.parseDateTime(date);
        }
        return null;
    }

    public static String displayFormat(DateTime dateTime) {
        String displayDateTime = null;
        if (dateTime != null) {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("MMM dd, yyyy hh:mm:ss aa");
            displayDateTime = fmt.print(dateTime);
        }
        return displayDateTime;
    }
}
