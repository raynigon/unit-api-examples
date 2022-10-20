package app.example.weather.service;

import org.springframework.stereotype.Service;

import javax.measure.Quantity;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Temperature;

import static com.raynigon.unit.api.core.units.si.SISystemUnitsConstants.Celsius;
import static com.raynigon.unit.api.core.units.si.SISystemUnitsConstants.Percent;

@Service
public class WeatherService {

    public Quantity<Temperature> getTemperature(){
        return Celsius(30);
    }

    public Quantity<Dimensionless> getHumidity(){
        return Percent(45);
    }
}
