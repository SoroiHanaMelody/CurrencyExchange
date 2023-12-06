package Main;


import API.api;
import CurrencyExchange.GenerateMatrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static API.getData.getRate;
import CurrencyExchange.CurrencyExchange;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) {
        //获取当前实时汇率数据
        api api = new api();
        String datasource = api.fetchData();
        // 从用户输入中获取货币代码字符串
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入所有货币代码，以 '/' 分隔：");
        String currencyInput = scanner.nextLine();

        // 将输入的货币代码字符串分割成数组
        String[] currencies = currencyInput.split("/");

        // 创建一个示例的汇率数据（你的实际情况可能需要从 API 或其他数据源中获取）
        Map<String, Double> exchangeRates = new HashMap<>();
        for (String currency : currencies) {
            String currencyCode = currency.trim();
            exchangeRates.put(currencyCode, getRate(currencyCode, datasource));
        }

        //创建 GenerateMatrix 实例并生成矩阵
        GenerateMatrix matrixGenerator = new GenerateMatrix();
        double[][] exchangeMatrix = matrixGenerator.createExchangeMatrix(currencies, exchangeRates);
        double[][] logExchangeMatrix = matrixGenerator.createlogExchangeMatrix(currencies, exchangeRates);

        // 打印生成的矩阵
        System.out.println("\nCurrency Matrix：");
        matrixGenerator.printMatrix(exchangeMatrix, currencies);
        System.out.println("\n-Log Currency Matrix：");
        matrixGenerator.printMatrix(logExchangeMatrix, currencies);
        System.out.println();

        //寻找套汇路径
        CurrencyExchange currencyExchange = new CurrencyExchange();
        currencyExchange.findArbitrage(exchangeMatrix, currencies);
    }
}
//case can be use
//CNY/EUR/HKD/IDR/JPY/KRW
//CNY/EUR/HKD/IDR/JPY/KRW/NZD
//CAD/CNY/EUR/HKD/IDR