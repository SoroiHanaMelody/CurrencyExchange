package API;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class getData {
    public static double getRate(String targetCurrency) {
        try {
            // 假设 response 是你从 API 获取的 JSON 数据
            String jsonResponse = "{\"data\":{\"CAD\":0.1890905263,\"CNY\":1.0,\"EUR\":0.1287280965,\"HKD\":1.0949156754,\"IDR\":2166.9195079492,\"JPY\":20.5203735316,\"KRW\":181.0852152354,\"NZD\":0.2254307609,\"USD\":0.1401714392}}";
            // 使用 Jackson ObjectMapper 解析 JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            // 获取指定货币的汇率
            double targetRate = jsonNode.path("data").path(targetCurrency).asDouble();
            System.out.println("汇率（" + targetCurrency + "）: " + targetRate);

            return targetRate;

        } catch (Exception e) {
            e.printStackTrace();

            return -1;
        }
    }
}

