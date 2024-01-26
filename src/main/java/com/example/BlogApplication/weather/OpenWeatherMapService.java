package com.example.BlogApplication.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// OpenWeatherMapService.java
@Service
public class OpenWeatherMapService implements WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private final String apiUrl = "http://api.openweathermap.org/data/2.5/weather";

    @Override
    public WeatherResponse getWeather(String city) {
        String url = apiUrl + "?q=" + city + "&appid=" + apiKey;

        // Make the HTTP request
        RestTemplate restTemplate = new RestTemplate();
        OpenWeatherMapResponse apiResponse = restTemplate.getForObject(url, OpenWeatherMapResponse.class);

        // Convert temperature from Kelvin to Celsius
        double temperatureInCelsius = Math.round((apiResponse.getMain().getTemp() - 273.15) * 10.0) / 10.0;

        // Extract relevant data from the API response and create a WeatherResponse object
        WeatherResponse weatherResponse = new WeatherResponse(city, temperatureInCelsius);

        return weatherResponse;
    }
}