package app.example.weather.dto;

import com.raynigon.unit.api.core.annotation.QuantityShape;
import com.raynigon.unit.api.core.units.si.dimensionless.Percent;
import com.raynigon.unit.api.core.units.si.temperature.Celsius;
import com.raynigon.unit.api.jackson.annotation.JsonUnit;

import javax.measure.Quantity;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Temperature;

public class WeatherResponseCelsius implements IWeatherResponse {

    @JsonUnit(value = Celsius.class, shape = QuantityShape.STRING)
    public final Quantity<Temperature> temperature;

    @JsonUnit(value = Percent.class, shape = QuantityShape.STRING)
    public final Quantity<Dimensionless> humidity;

    public WeatherResponseCelsius(Quantity<Temperature> temperature, Quantity<Dimensionless> humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }
}
