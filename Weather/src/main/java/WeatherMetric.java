import java.util.List;

public record WeatherMetric(double lat, double lon, int currentTemp, List<Integer> dayTemps) {

    public WeatherMetric(double lat,
                         double lon,
                         int currentTemp,
                         List<Integer> dayTemps) {
        this.lat = lat;
        this.lon = lon;
        this.currentTemp = currentTemp;
        this.dayTemps = List.copyOf(dayTemps);
    }
}