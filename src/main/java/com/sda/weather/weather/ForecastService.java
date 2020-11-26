package com.sda.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForecastService {
    private final ForecastRepository forecastRepository;


    Forecast createForecast(Forecast forecast) {

        ForecastConnection forecastConnection = new ForecastConnection();
        ForecastDto forecastDto = forecastConnection.getForecast();
        forecast.setAirPressure(forecastDto.getAirPressure());
        forecast.setHumidity(forecastDto.getHumidity());
        forecast.setTemperature(forecastDto.getTemperature());
        forecast.setWindDirect(forecastDto.getWindDirect());
        forecast.setWindSpeed(forecastDto.getWindSpeed());


        return forecastRepository.save(forecast);
    }
}
