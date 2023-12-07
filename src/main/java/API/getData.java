package API;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class getData {
    public static double getRate(String targetCurrency, String datasource) {
        try {
            // Use Jackson ObjectMapper to analysis JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(datasource);

            // Get the exchange rate of target currency
            JsonNode currencyNode = jsonNode.path("data").path(targetCurrency);
            double targetRate = currencyNode.path("value").asDouble();
            System.out.println("Currency（" + targetCurrency + "）: " + targetRate);

            return targetRate;

        } catch (Exception e) {
            e.printStackTrace();

            return -1;
        }
    }
}

