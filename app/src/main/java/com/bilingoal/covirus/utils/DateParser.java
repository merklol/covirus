package com.bilingoal.covirus.utils;

import com.bilingoal.covirus.dto.Country;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {
    public static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String UNKNOWN_DATE = "Unknown";

    public static String parseDate(Country country){
        try {
            DateFormat dateFormat = new SimpleDateFormat(PATTERN, Locale.getDefault());
            Date date = dateFormat.parse(country.getDate());
            return date != null ? date.toString() : UNKNOWN_DATE;
        } catch (ParseException e) {
            e.printStackTrace();
            return UNKNOWN_DATE;
        }
    }
}
