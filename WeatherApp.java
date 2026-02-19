import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class WeatherApp {

    public static void main(String[] args) {

        try {
            String apiKey = "1136e20efe1f6eb6e3999db0f5e7bbda";  
            String city = "Hyderabad";

            String urlString = "https://api.openweathermap.org/data/2.5/weather?q="
                    + city + "&appid=" + apiKey + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(response.toString());

            String cityName = jsonObject.getString("name");
            double temperature = jsonObject.getJSONObject("main").getDouble("temp");
            int humidity = jsonObject.getJSONObject("main").getInt("humidity");
            String weather = jsonObject.getJSONArray("weather")
                                       .getJSONObject(0)
                                       .getString("description");

            System.out.println("------ Weather Details ------");
            System.out.println("City: " + cityName);
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Condition: " + weather);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
