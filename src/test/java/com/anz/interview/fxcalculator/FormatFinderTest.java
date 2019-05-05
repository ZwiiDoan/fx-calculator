package com.anz.interview.fxcalculator;

import org.junit.Test;

import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FormatFinderTest {

    private ResourceBundle currencyFormats = ResourceBundle.getBundle("test_currency_format");

    @Test
    public void shouldFindJPYFormat() {
        assertThat(FormatFinder.findFormat(currencyFormats, "JPY"), is("%.0f"));
    }

    @Test
    public void shouldUseDefaultFormat() {
        assertThat(FormatFinder.findFormat(currencyFormats, "VND"), is("%.2f"));
    }
}