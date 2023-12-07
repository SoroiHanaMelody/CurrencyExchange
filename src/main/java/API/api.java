package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class api {
    public String fetchData() {
        try {
            // Set the API and request URL
            String apiUrl = "https://api.currencyapi.com/v3/latest?apikey=fca_live_I0crO06xVtiV7EhPO3PTCiZ9oTBCzFKaaGGCOtBM&currencies=&base_currency=CNY";
            URL url = new URL(apiUrl);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response content
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Close connection
                connection.disconnect();

                // Return response content
                return response.toString();
            } else {
                System.out.println("Request failed, response code：" + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return empty string if failed
        return "";
    }
}

