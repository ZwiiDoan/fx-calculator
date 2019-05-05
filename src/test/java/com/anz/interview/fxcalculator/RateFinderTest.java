package com.anz.interview.fxcalculator;

import org.junit.Test;

import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RateFinderTest {

    private ResourceBundle currencyRates = ResourceBundle.getBundle("test_currency_rate");
    private ResourceBundle crossViaPairs = ResourceBundle.getBundle("test_cross_via");

    /**
     * not shouldFound
     */
    @Test
    public void shouldNotFindRateKRWCNY() {
        assertThat(RateFinder.findRate("KRW", "CNY", currencyRates, crossViaPairs), is(nullValue()));
    }

    /**
     * direct shouldFeed
     */
    @Test
    public void shouldFindRateUSDCNY() {
        assertEqualRates(RateFinder.findRate("USD", "CNY", currencyRates, crossViaPairs), 6.1715);
    }

    @Test
    public void shouldFindRateGBPUSD() {
        assertEqualRates(RateFinder.findRate("GBP", "USD", currencyRates, crossViaPairs), 1.5683);
    }

    /**
     * invert
     */
    @Test
    public void shouldFindRateCNYUSD() {
        assertEqualRates(RateFinder.findRate("CNY", "USD", currencyRates, crossViaPairs), 1 / 6.1715);
    }

    @Test
    public void shouldFindRateUSDGBP() {
        assertEqualRates(RateFinder.findRate("USD", "GBP", currencyRates, crossViaPairs), 1 / 1.5683);
    }


    /**
     * cross via 1 currency
     */
    @Test
    public void shouldFindRateAUDJPY() {
        assertEqualRates(RateFinder.findRate("AUD", "JPY", currencyRates, crossViaPairs), 100.410145);
    }

    @Test
    public void shouldFindRateJPYAUD() {
        assertEqualRates(RateFinder.findRate("JPY", "AUD", currencyRates, crossViaPairs), 0.009959153031797735);
    }

    @Test
    public void shouldFindRateNOKUSD() {
        assertEqualRates(RateFinder.findRate("NOK", "USD", currencyRates, crossViaPairs), 0.14212184510276857);
    }

    @Test
    public void shouldFindRateUSDNOK() {
        assertEqualRates(RateFinder.findRate("USD", "NOK", currencyRates, crossViaPairs), 7.036215996751928);
    }

    /**
     * cross via 2 currencies
     */
    @Test
    public void shouldFindRateAUDNOK() {
        assertEqualRates(RateFinder.findRate("AUD", "NOK", currencyRates, crossViaPairs), 5.890016410881039);
    }

    @Test
    public void shouldFindRateNOKAUD() {
        assertEqualRates(RateFinder.findRate("NOK", "AUD", currencyRates, crossViaPairs), 0.169778814);
    }


    /**
     * cross via and invert
     */
    @Test
    public void shouldFindRateDKKUSD() {
        assertEqualRates(RateFinder.findRate("DKK", "USD", currencyRates, crossViaPairs), 0.16551307);
    }

    @Test
    public void shouldFindRateUSDDKK() {
        assertEqualRates(RateFinder.findRate("USD", "DKK", currencyRates, crossViaPairs), 6.04181892);
    }

    /**
     * Cannot cross via
     */
    @Test
    public void shouldNotFindRateGBPVND() {
        assertThat(RateFinder.findRate("GBP", "VND", currencyRates, crossViaPairs), is(nullValue()));
    }

    private void assertEqualRates(Double rate1, Double rate2) {
        assertThat(String.format("%.4f", rate1), is(String.format("%.4f", rate2)));
    }


}