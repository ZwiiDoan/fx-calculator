package com.anz.interview.fxcalculator;

import java.util.ResourceBundle;

class FormatFinder {

    private static final String DEFAULT_FORMAT = "DEFAULT";

    static String findFormat(ResourceBundle currencyFormats, String currency) {
        try {
            return currencyFormats.getString(currency.toUpperCase());
        } catch (Exception ex) {
            return currencyFormats.getString(DEFAULT_FORMAT);
        }
    }
}
