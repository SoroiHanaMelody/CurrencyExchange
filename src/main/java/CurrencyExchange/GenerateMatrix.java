package CurrencyExchange;

import java.util.Map;

public class GenerateMatrix {
    public double[][] createExchangeMatrix(String[] currencies, Map<String, Double> exchangeRates) {
        int n = currencies.length;
        double[][] exchangeMatrix = new double[n][n];
        double[][] logExchangeMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    // Diagonal element with an exchange rate value of 1 (each currency's exchange rate relative to its own)
                    exchangeMatrix[i][j] = 1.0;
                } else {
                    // Use the obtained exchange rate value, if there is no specific exchange rate, you can consider setting the missing value to the default value or special value
                    String fromCurrency = currencies[i];
                    String toCurrency = currencies[j];
                    double exchangeRate = exchangeRates.getOrDefault(toCurrency, 0.0) / exchangeRates.getOrDefault(fromCurrency, 1.0);

                    // Store the value in the matrix
                    logExchangeMatrix[i][j] = -Math.log10(exchangeRate);
                    exchangeMatrix[i][j] = exchangeRate;
                }
            }
        }

        return exchangeMatrix;
    }

    public double[][] createlogExchangeMatrix(String[] currencies, Map<String, Double> exchangeRates) {
        int n = currencies.length;
        double[][] logExchangeMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    // Diagonal element with an exchange rate value of 1 (each currency's exchange rate relative to its own)
                    logExchangeMatrix[i][j] = 1.0;
                } else {
                    // Use the obtained exchange rate value, if there is no specific exchange rate, you can consider setting the missing value to the default value or special value
                    String fromCurrency = currencies[i];
                    String toCurrency = currencies[j];
                    double exchangeRate = exchangeRates.getOrDefault(toCurrency, 0.0) / exchangeRates.getOrDefault(fromCurrency, 1.0);

                    // Store the value in the matrix
                    logExchangeMatrix[i][j] = -Math.log10(exchangeRate);
                }
            }
        }

        return logExchangeMatrix;
    }

    public void printMatrix(double[][] matrix, String[] currencies) {
        // Print the matrix
        System.out.print("\t\t\t");
        // Print the currency code
        for (String currency : currencies) {
            System.out.printf("%-10s\t", currency);
        }
        System.out.println();

        // Print the exchange rate
        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("%-10s\t", currencies[i]);
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%.10f\t\t", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
