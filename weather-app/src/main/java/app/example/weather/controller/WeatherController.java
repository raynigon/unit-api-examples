package app.example.weather.controller;

import app.example.weather.dto.WeatherResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.raynigon.unit.api.core.units.si.SISystemUnitsConstants.Celsius;
import static com.raynigon.unit.api.core.units.si.SISystemUnitsConstants.Percent;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @GetMapping
    public WeatherResponse getWeather() {
        return new WeatherResponse(Celsius(30), Percent(45));
    }
}
