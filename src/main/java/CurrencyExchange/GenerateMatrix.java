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
                    // 对角线上的元素，汇率值为 1（每种货币相对于自己的汇率）
                    exchangeMatrix[i][j] = 1.0;
                } else {
                    // 使用获得的汇率值，如果没有特定的汇率，可以考虑将缺失的值设置为默认值或特殊值
                    String fromCurrency = currencies[i];
                    String toCurrency = currencies[j];
                    double exchangeRate = exchangeRates.getOrDefault(toCurrency, 0.0) / exchangeRates.getOrDefault(fromCurrency, 1.0);

                    // 计算-log值并存储到矩阵中
                    logExchangeMatrix[i][j] = -Math.log(exchangeRate);
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
                    // 对角线上的元素，汇率值为 1（每种货币相对于自己的汇率）
                    logExchangeMatrix[i][j] = 1.0;
                } else {
                    // 使用获得的汇率值，如果没有特定的汇率，可以考虑将缺失的值设置为默认值或特殊值
                    String fromCurrency = currencies[i];
                    String toCurrency = currencies[j];
                    double exchangeRate = exchangeRates.getOrDefault(toCurrency, 0.0) / exchangeRates.getOrDefault(fromCurrency, 1.0);

                    // 计算-log值并存储到矩阵中
                    logExchangeMatrix[i][j] = -Math.log(exchangeRate);
                }
            }
        }

        return logExchangeMatrix;
    }

    public void printMatrix(double[][] matrix, String[] currencies) {
        // 打印矩阵
        System.out.print("\t\t\t");
        for (String currency : currencies) {
            System.out.printf("%-10s\t", currency);
        }
        System.out.println();

        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("%-10s\t", currencies[i]);
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%.4f\t\t", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
