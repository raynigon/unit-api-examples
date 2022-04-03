package app.example.weather.controller;

import app.example.weather.dto.IWeatherResponse;
import app.example.weather.dto.WeatherResponseCelsius;
import app.example.weather.dto.WeatherResponseKelvin;
import app.example.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/weather")
public class WeatherControllerV2 {

    private WeatherService weatherService;

    public WeatherControllerV2(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public IWeatherResponse getWeather(@RequestParam String unit) {
        if (unit.equalsIgnoreCase("K")) {
            return new WeatherResponseKelvin(weatherService.getTemperature(), weatherService.getHumidity());
        }
        return new WeatherResponseCelsius(weatherService.getTemperature(), weatherService.getHumidity());
    }
}
