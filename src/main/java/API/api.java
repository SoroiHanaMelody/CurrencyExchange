package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class api {
    public static void main(String[] args) {
        try {
            // 设置 API 请求 URL
            String apiUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_I0crO06xVtiV7EhPO3PTCiZ9oTBCzFKaaGGCOtBM&currencies=CNY%2CEUR%2CUSD%2CCAD%2CJPY%2CHKD%2CNZD%2CKRW%2CIDR&base_currency=CNY";
            URL url = new URL(apiUrl);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法
            connection.setRequestMethod("GET");

            // 获取响应代码
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 读取响应内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // 处理响应数据，这里简单输出到控制台
                System.out.println(response.toString());
            } else {
                System.out.println("请求失败，响应代码：" + responseCode);
            }

            // 关闭连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

