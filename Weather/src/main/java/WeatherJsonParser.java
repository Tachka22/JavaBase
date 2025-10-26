import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;

public class WeatherJsonParser {

    public static WeatherMetric parse(String jsonResponse) {
        JSONObject root = new JSONObject(jsonResponse);

        var lat = root.getJSONObject("info").getDouble("lat");
        var lon = root.getJSONObject("info").getDouble("lon");
        var currentTemp = root.getJSONObject("fact").getInt("temp");

        JSONArray forecasts = root.getJSONArray("forecasts");
        List<Integer> days = new ArrayList<>();

        for (int i = 0; i < forecasts.length(); i++) {
            var parts = forecasts.getJSONObject(i).getJSONObject("parts");
            days.add(parts.getJSONObject("day").getInt("temp_avg"));
        }

        return new WeatherMetric(lat, lon, currentTemp, days);
    }
}