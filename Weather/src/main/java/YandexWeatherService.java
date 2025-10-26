import java.util.HashMap;
import java.util.Map;

public class YandexWeatherService implements  WeatherService {
    private static final String BASE_URL = "https://api.weather.yandex.ru/v2/forecast";
    private static final String HEADER_KEY = "X-Yandex-Weather-Key";

    private final String apiKey;
    private final HttpService httpService;

    public YandexWeatherService(String apiKey, HttpService httpService) {
        this.apiKey = apiKey;
        this.httpService = httpService;
    }

    @Override
    public String getForecastJSON(double lat, double lon, int days) {
        String url = String.format("%s?lat=%s&lon=%s&limit=%d", BASE_URL, lat, lon, days);

        Map<String, String> headers = new HashMap<>();
        headers.put(HEADER_KEY, apiKey);

        return httpService.Get(url, headers);
    }
}
