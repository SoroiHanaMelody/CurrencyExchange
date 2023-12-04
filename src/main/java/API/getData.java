package API;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class getData {
    public static double getRate(String targetCurrency) {
        try {
            // 假设 response 是你从 API 获取的 JSON 数据
            String jsonResponse = "{\"data\":{\"CAD\":0.1890905263,\"EUR\":0.1287280965,\"USD\":0.140087792}}";

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

