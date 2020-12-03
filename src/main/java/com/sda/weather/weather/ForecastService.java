package com.sda.weather.weather;

import com.sda.weather.exception.NotFoundException;
import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationDTO;
import com.sda.weather.localization.LocalizationFetchService;
import com.sda.weather.localization.LocalizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class ForecastService {

    private final ForecastRepository forecastRepository;
    private final ForecastConnection forecastConnection;
    private final LocalizationFetchService localizationFetchService;
    private final LocalizationMapper localizationMapper;


    Forecast getForecast(Forecast forecast) {
        ForecastDto forecastDto = forecastConnection.getForecast();
        forecast.setAirPressure(forecastDto.getAirPressure());
        forecast.setHumidity(forecastDto.getHumidity());
        forecast.setTemperature(forecastDto.getTemperature());
        forecast.setWindDirect(forecastDto.getWindDirect());
        forecast.setWindSpeed(forecastDto.getWindSpeed());

        return forecastRepository.save(forecast);
    }

    Forecast getForecastByCityAndPeriod(String locationName, Integer period) {
        return forecastRepository.findByCityAndPeriod(locationName, period)
                .orElseThrow(() -> new NotFoundException("Nie można znależć danych"));
    }


    public ForecastDto getForecastByIDLocaliztion(Long id, Integer period, Localization localization) {
        localization = localizationFetchService.fetchLocalization(id);
        LocalizationDTO localizationDTO = localizationMapper.mapToLocalizationDto(localization);
        return null;
    }
}
