package API;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class getData {
    public static double getRate(String targetCurrency, String datasource) {
        try {
            // 使用 Jackson ObjectMapper 解析 JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(datasource);

            // 获取指定货币的汇率
            JsonNode currencyNode = jsonNode.path("data").path(targetCurrency);
            double targetRate = currencyNode.path("value").asDouble();
            System.out.println("汇率（" + targetCurrency + "）: " + targetRate);

            return targetRate;

        } catch (Exception e) {
            e.printStackTrace();

            return -1;
        }
    }
}

