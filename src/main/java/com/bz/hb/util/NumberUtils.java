package com.bz.hb.util;

/**
 * created by srana on 07/10/20 at 10.52 AM
 */


import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
public final class NumberUtils {

    private static final char[] BENGALI_NUMBERS = {'\u09E6', '\u09E7', '\u09E8', '\u09E9', '\u09EA', '\u09EB', '\u09EC', '\u09ED', '\u09EE', '\u09EF'};
    private static final String BENGALI_NUMBERS_STRING = "০১২৩৪৫৬৭৮৯";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public static String toBengali(int value) {
        return toBengali(String.valueOf(value));
    }

    public static String toBengali(long value) {
        return toBengali(String.valueOf(value));
    }

    public static String toBengali(double value) {
        String valueString = DECIMAL_FORMAT.format(value);
        return toBengali(valueString);
    }

    public static String toBengali(BigDecimal value) {
        String valueString = DECIMAL_FORMAT.format(value);
        return toBengali(valueString);
    }

    public static String toBengali(String number) {
        return convertToBengali(number);
    }

    public static String toEnglish(String number) {
        return convertToEnglish(number);
    }

    private static String convertToBengali(String input) {
        if (StringUtils.isBlank(input)) {
            return input;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isDigit(ch)) {
                int numericValue = Character.getNumericValue(ch);
                if (numericValue >= 0) {
                    builder.append(BENGALI_NUMBERS[numericValue]);
                }
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    private static String convertToEnglish(String input) {
        if (StringUtils.isBlank(input)) {
            return input;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int index = BENGALI_NUMBERS_STRING.indexOf(ch);
            if (index > -1) {
                builder.append(index);
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }
}
