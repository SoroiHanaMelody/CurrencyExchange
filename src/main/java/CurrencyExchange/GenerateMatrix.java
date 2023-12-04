package CurrencyExchange;

import java.util.HashMap;
import java.util.Map;

import static API.getData.getRate;

public class GenerateMatrix {

//    public static void main(String[] args) {
//        // 假设这是你获得的汇率数据
//        Map<String, Double> exchangeRates = new HashMap<>();
//        exchangeRates.put("CNY", 1.0);
//        exchangeRates.put("USD", getRate("USD"));
//        exchangeRates.put("EUR", getRate("EUR"));
//        exchangeRates.put("CAD", getRate("CAD"));
//        // 添加其他货币汇率
//
//        // 定义货币列表
//        String[] currencies = exchangeRates.keySet().toArray(new String[0]);
//
//        // 创建 n×n 矩阵
//        double[][] exchangeMatrix = createExchangeMatrix(currencies, exchangeRates);
//
//        // 打印矩阵
//        printMatrix(exchangeMatrix, currencies);
//    }

    public double[][] createExchangeMatrix(String[] currencies, Map<String, Double> exchangeRates) {
        int n = currencies.length;
        double[][] exchangeMatrix = new double[n][n];

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
                    //exchangeMatrix[i][j] = -Math.log(exchangeRate);
                    exchangeMatrix[i][j] = exchangeRate;
                }
            }
        }

        return exchangeMatrix;
    }

    public void printMatrix(double[][] matrix, String[] currencies) {
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
