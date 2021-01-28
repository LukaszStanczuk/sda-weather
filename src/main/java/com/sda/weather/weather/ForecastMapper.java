package com.sda.weather.weather;

import com.sda.weather.localization.Localization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor

public class ForecastMapper {
    private final DateManager dateManager;
    private final ForecastWindDirectionMapper forecastWindDirectionMapper;

    ForecastDto mapToForecastDto(Forecast forecast) {
        return ForecastDto.builder()
                .windSpeed(forecast.getWindSpeed())
                .windDirect(forecastWindDirectionMapper.directCalculate(forecast.getWindDirect()))
                .temperature(forecast.getTemperature())
                .humidity(forecast.getHumidity())
                .airPressure(forecast.getAirPressure())
                .build();
    }

    Forecast mapToNewForecast(ForecastOpenWeather.SingleForecast forecast,
                              Localization localization) {
        Forecast newForecast = new Forecast();
        newForecast.setLocalizations(localization);
        newForecast.setTemperature(forecast.getMain().getTemperature());
        newForecast.setHumidity(forecast.getMain().getAirHumidity());
        newForecast.setAirPressure(forecast.getMain().getAirPressure());
        newForecast.setWindSpeed(forecast.getWind().getWindSpeed());
        newForecast.setWindDirect(forecast.getWind().getWindDirection());
        newForecast.setForecastDate(dateManager.instantDateConverter(dateManager.localDateTimeConverter(forecast.getDate())));

        return newForecast;
    }

}
