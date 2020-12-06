package com.sda.weather.weather;

import org.springframework.stereotype.Component;

@Component
public class ForecastMapper {
    ForecastDto mapToForecastDto(Forecast forecast) {
        return ForecastDto.builder()
                .windSpeed(forecast.getWindSpeed())
                .windDirect(forecast.getWindDirect())
                .temperature(forecast.getTemperature())
                .humidity(forecast.getHumidity())
                .airPressure(forecast.getAirPressure())
                .build();
    }
}
