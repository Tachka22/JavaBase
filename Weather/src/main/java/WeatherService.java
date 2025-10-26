public interface WeatherService {
    String getForecastJSON(double lat, double lon, int days);
}
