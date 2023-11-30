package CurrencyExchange;

import java.util.HashMap;
import java.util.Map;
import API.getData;

import static API.getData.getRate;

public class generateMatrix {

    public static void main(String[] args) {
        // 假设这是你获得的汇率数据
        Map<String, Double> exchangeRates = new HashMap<>();
        exchangeRates.put("USD", getRate("USD"));
        exchangeRates.put("EUR", getRate("EUR"));
        exchangeRates.put("JPY", getRate("JPY"));
        // 添加其他货币汇率

        // 定义货币列表
        String[] currencies = exchangeRates.keySet().toArray(new String[0]);

        // 创建 n×n 矩阵
        double[][] exchangeMatrix = createExchangeMatrix(currencies, exchangeRates);

        // 打印矩阵
        printMatrix(exchangeMatrix, currencies);
    }

    private static double[][] createExchangeMatrix(String[] currencies, Map<String, Double> exchangeRates) {
        int n = currencies.length;
        double[][] matrix = new double[n][n];

        // 填充矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    // 对角线上的元素，汇率值为 1（每种货币相对于自己的汇率）
                    matrix[i][j] = 1.0;
                } else {
                    // 使用获得的汇率值，如果没有特定的汇率，可以考虑将缺失的值设置为默认值或特殊值
                    String fromCurrency = currencies[i];
                    String toCurrency = currencies[j];
                    matrix[i][j] = exchangeRates.getOrDefault(toCurrency, 0.0) / exchangeRates.getOrDefault(fromCurrency, 1.0);
                }
            }
        }

        return matrix;
    }

    private static void printMatrix(double[][] matrix, String[] currencies) {
        // 打印矩阵
        System.out.print("\t");
        for (String currency : currencies) {
            System.out.print(currency + "\t");
        }
        System.out.println();

        for (int i = 0; i < matrix.length; i++) {
            System.out.print(currencies[i] + "\t");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%.4f\t", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
