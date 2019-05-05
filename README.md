# FX Calculator
This is FX Calculator - a console-based currency converter application

The application allows a user to convert an amount in a specific currency to the equivalent amount in another currency.

The calculator should allow a user to enter an amount in any of the known currencies, and provide the equivalent amount in another
currency.

The calculator should parse console input like "<ccy1> <amount1> in <ccy2>", and provide a suitable response.

For example:
%> AUD 100.00 in USD

If the rate cannot be calculated, the program should alert the user:
%> KRW 1000.00 in FJD
Unable to find rate for KRW/FJD

When displaying amounts back to the user, they should be formatted with the precision given in src/main/resources/currency_format.properties

The list of currency pairs and rates that are given is in src/main/resources/currency_rate.properties

In order to help with the conversion, the "cross-via" currencies are defined in src/main/resources/cross_via.properties

## Installation
Install [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Apache Maven](https://maven.apache.org/install.html) to build and run the application

```bash
$ cd {your_path}/fx-calculator-deliverable
$ mvn clean package
$ java -jar ./target/fx-calculator-1.0-SNAPSHOT.jar
```

## Contributor
1. Zwii Doan - [zwii.doan@gmail.com](mailto:zwii.doan@gmail.com)

## License
[MIT](https://choosealicense.com/licenses/mit/)