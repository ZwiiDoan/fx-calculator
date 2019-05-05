package com.anz.interview.fxcalculator;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

class RateFinder {
    private static final String DEFAULT_CROSS_VIA = "DEFAULT";

    static Double findRate(String ccy1, String ccy2, ResourceBundle currencyRates, ResourceBundle crossViaPairs) {
        if (ccy1.equals(ccy2)) {
            return 1d;
        }

        List<String> currencyPairs = Collections.list(currencyRates.getKeys());
        if (currencyNotFound(currencyPairs, ccy1) || currencyNotFound(currencyPairs, ccy2)) {
            return null;
        }

        Double rate = findRate(currencyRates, ccy1 + ccy2);
        if (rate != null) {
            return rate;
        }

        rate = findRate(currencyRates, ccy2 + ccy1);
        if (rate != null) {
            return 1 / rate;
        }

        return findCrossViaRate(ccy1, ccy2, currencyRates, crossViaPairs);
    }

    private static Double findCrossViaRate(String ccy1, String ccy2, ResourceBundle currencyRates, ResourceBundle crossViaPairs) {
        String crossVia = findCrossVia(crossViaPairs, ccy1 + ccy2);

        if (crossVia != null) {
            return findCrossViaRate(ccy1, ccy2, currencyRates, crossViaPairs, crossVia);
        }

        crossVia = findCrossVia(crossViaPairs, ccy2 + ccy1);
        if (crossVia != null) {
            return findCrossViaRate(ccy1, ccy2, currencyRates, crossViaPairs, crossVia);
        }

        String defaultCrossVia = crossViaPairs.getString(DEFAULT_CROSS_VIA).toUpperCase();
        return findCrossViaRate(ccy1, ccy2, currencyRates, crossViaPairs, defaultCrossVia);
    }

    private static Double findCrossViaRate(String ccy1, String ccy2, ResourceBundle currencyRates, ResourceBundle crossViaPairs, String crossVia) {
        // Detect endless recursive findCrossViaRate when no cross via is usable, see test case RateFinderTest#findRateGBPVND
        String defaultCrossVia = crossViaPairs.getString(DEFAULT_CROSS_VIA).toUpperCase();
        if (defaultCrossVia.equals(crossVia) && (defaultCrossVia.equals(ccy1) || defaultCrossVia.equals(ccy2))) {
            return null;
        }

        Double rate1 = findRate(ccy1, crossVia, currencyRates, crossViaPairs);
        Double rate2 = findRate(crossVia, ccy2, currencyRates, crossViaPairs);

        if (rate1 != null && rate2 != null) {
            return rate1 * rate2;
        } else {
            return null;
        }
    }

    private static boolean currencyNotFound(List<String> currencyPairs, String currency) {
        for (String currencyPair : currencyPairs) {
            if (currencyPair.contains(currency)) {
                return false;
            }
        }

        return true;
    }

    private static Double findRate(ResourceBundle currencyRates, String key) {
        try {
            return Double.parseDouble(currencyRates.getString(key));
        } catch (Exception ex) {
            return null;
        }
    }

    private static String findCrossVia(ResourceBundle crossViaPair, String key) {
        try {
            return crossViaPair.getString(key);
        } catch (Exception ex) {
            return null;
        }
    }
}
