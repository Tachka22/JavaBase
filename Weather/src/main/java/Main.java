import java.util.List;

public class Main {
    private static final String YANDEX_WEATHER_KEY = "";//тут ключ апи

    public static void main(String[] args) {
        var lat = 55.75;
        var lon = 37.62;
        var days = 7;

        try {
            HttpService httpService = new HttpService();
            WeatherService weatherService = new YandexWeatherService(YANDEX_WEATHER_KEY, httpService);

            var json = weatherService.getForecastJSON(lat, lon, days);
            System.out.println(json);

            var metric = WeatherJsonParser.parse(json);
            printWeather(metric, days);
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static void printWeather(WeatherMetric w, int daysLimit) {
        var avgDay = calculateAverage(w.dayTemps(), daysLimit);

        System.out.printf("Координаты: Широта %.2f, Долгота %.2f%n", w.lat(), w.lon());
        System.out.printf("Текущая температура: %d°C%n", w.currentTemp());
        System.out.printf("Средняя температура на ближайшие %d дней: ", daysLimit);
        System.out.printf("%.1f°C%n", avgDay);
    }

    private static double calculateAverage(List<Integer> temps, int limit) {
        if (limit <= 0) {
            return Double.NaN;
        }

        return temps.stream()
                .limit(limit)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(Double.NaN);
    }
}