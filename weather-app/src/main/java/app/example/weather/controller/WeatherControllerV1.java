package app.example.weather.controller;

import app.example.weather.dto.WeatherResponseCelsius;
import app.example.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherControllerV1 {

  private WeatherService weatherService;

  public WeatherControllerV1(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping
  public WeatherResponseCelsius getWeather() {
    return new WeatherResponseCelsius(
        weatherService.getTemperature(), weatherService.getHumidity());
  }
}
