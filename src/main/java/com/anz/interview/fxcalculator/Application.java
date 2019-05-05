package com.anz.interview.fxcalculator;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        try {
            ResourceBundle currencyRates = ResourceBundle.getBundle("currency_rate");
            ResourceBundle currencyFormats = ResourceBundle.getBundle("currency_format");
            ResourceBundle crossViaPairs = ResourceBundle.getBundle("cross_via");

            System.out.println("===== FX Calculator =====");

            String input = null;

            while (!"quit".equalsIgnoreCase(input)) {
                try {
                    System.out.println("Please input \"<ccy1> <amount1> in <ccy2>\" or \"quit\" and press Enter");
                    System.out.println("For instance: AUD 100.00 in USD");

                    Scanner scanner = new Scanner(System.in);
                    scanner.useDelimiter("\n");

                    input = scanner.nextLine();

                    if (input == null || "".equals(input.trim())) {
                        throw new RuntimeException();
                    }

                    String[] inputValues = input.trim().replaceAll(" +", " ").split(" ");

                    if (inputValues.length != 4) {
                        throw new RuntimeException();
                    }

                    String ccy1 = inputValues[0].toUpperCase();
                    double ccy1Amount = Double.parseDouble(inputValues[1]);
                    String ccy2 = inputValues[3].toUpperCase();

                    Double rate = RateFinder.findRate(ccy1, ccy2, currencyRates, crossViaPairs);

                    if (rate == null) {
                        System.out.println(String.format("Unable to find rate for %s/%s", ccy1, ccy2));
                    } else {
                        String ccy1Format = FormatFinder.findFormat(currencyFormats, ccy1);
                        String ccy2Format = FormatFinder.findFormat(currencyFormats, ccy2);
                        double ccy2Amount = ccy1Amount * rate;

                        System.out.println(
                                String.format("%s %s = %s %s",
                                        ccy1, String.format(ccy1Format, ccy1Amount),
                                        ccy2, String.format(ccy2Format, ccy2Amount)
                                )
                        );
                    }

                } catch (Exception ex) {
                    //Omit exception until user type "quit"
                    System.out.println("Invalid input, please retry...");
                }

                System.out.println("-------------------------");
            }
        } catch (Exception ex) {
            System.out.println("Unexpected error occurs, please contact author for support");
            ex.printStackTrace();
        }
    }

}
