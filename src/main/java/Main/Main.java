package Main;


import API.api;
import CurrencyExchange.GenerateMatrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static API.getData.getRate;
import CurrencyExchange.CurrencyExchange;

public class Main {
    public static void main(String[] args) {
        // Get current real-time exchange rate data
        api api = new api();
        String datasource = api.fetchData();
        // Gets the currency code string from user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter all currency codes，divided by '/'：");
        String currencyInput = scanner.nextLine();

        // Split the currency code string into an array
        String[] currencies = currencyInput.split("/");

        // Get the exchange rate of each currency
        Map<String, Double> exchangeRates = new HashMap<>();
        for (String currency : currencies) {
            String currencyCode = currency.trim();
            exchangeRates.put(currencyCode, getRate(currencyCode, datasource));
        }

        // Generate the exchange rate matrix
        GenerateMatrix matrixGenerator = new GenerateMatrix();
        double[][] exchangeMatrix = matrixGenerator.createExchangeMatrix(currencies, exchangeRates);
        double[][] logExchangeMatrix = matrixGenerator.createlogExchangeMatrix(currencies, exchangeRates);

        // Print the matrix
        System.out.println("\nCurrency Matrix：");
        matrixGenerator.printMatrix(exchangeMatrix, currencies);
        System.out.println("\n-Log Currency Matrix：");
        matrixGenerator.printMatrix(logExchangeMatrix, currencies);
        System.out.println();

        // Find the arbitrage opportunity
        CurrencyExchange currencyExchange = new CurrencyExchange();
        currencyExchange.findArbitrage(exchangeMatrix, currencies);
    }
}
//case can be use
//CAD/CNY/EUR/HKD/IDR
//CNY/EUR/HKD/JPY/KRW