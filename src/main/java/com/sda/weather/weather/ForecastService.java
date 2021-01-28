package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.exception.CriticalError;
import com.sda.weather.exception.NotFoundException;
import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class ForecastService {

    private final LocalizationFetchService localizationFetchService;
    private final ForecastRepository forecastRepository;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final DateManager dateManager;
    private final ForecastMapper forecastMapper;

    Forecast getForecast(Long id, Integer period) {
        Localization localization = localizationFetchService.fetchLocalization(id);
        String cityName = localization.getCity();

        String url = UriComponentsBuilder.newInstance()
                .scheme("http").host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", cityName)
                .queryParam("appid", "3b26fcb0c4ae6a52325337d9a0f3690b")
                .queryParam("units", "metric")
                .build().toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String response = entity.getBody();

        try {
            ForecastOpenWeather forecast = objectMapper.readValue(response, ForecastOpenWeather.class);
            ForecastOpenWeather.SingleForecast forecastFromSingleForecast = forecast
                    .getList()
                    .stream()
                    .filter(singleForecast -> dateManager.localDateTimeConverter(singleForecast.getDate())
                            .equals(dateManager.nowDatePlusPeriod(period)))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Cannot find forecast for " + cityName));
            return forecastRepository
                    .save(forecastMapper.mapToNewForecast(forecastFromSingleForecast, localization));

        } catch (JsonProcessingException e) {
            throw new CriticalError("Critical error: " + e.getMessage());
        }
    }


}


