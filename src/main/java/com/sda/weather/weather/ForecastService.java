package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("unable to connect to server");
        }

        ForecastOpenWeather forecastOpenWeather = null;
        try {
            forecastOpenWeather = objectMapper.readValue(response, ForecastOpenWeather.class);
            LocalDate now = LocalDate.now();
            LocalDate forecacastDate = now.plusDays(period);
            LocalDateTime localDateTime = forecacastDate.atTime(12, 00, 00);
            List<ForecastOpenWeather.SingleForecast> singleForecasts = forecastOpenWeather.getList();
            return singleForecasts.stream()
                    .filter(sf -> maptoLocalDateTime(sf.getDate()).isEqual(localDateTime))
                    .map(sf -> mapToForecast(sf))
                    .findFirst();

        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();

        }
        return ()
    }


    private Forecast mapToForecast(ForecastOpenWeather.SingleForecast singleForecast) {
        LocalDateTime forecastDate = maptoLocalDateTime(singleForecast.getDate());
        Instant forecastDateInstant = forecastDate.atZone(ZoneId.systemDefault()).toInstant();

        Forecast forecast = new Forecast();
        forecast.setWindSpeed(singleForecast.getWind().getSpeed());
        forecast.setWindDirect(singleForecast.getWind().getDeg());
        forecast.setTemperature(singleForecast.getMain().getTemp());
        forecast.setHumidity(singleForecast.getMain().getHumidity());
        forecast.setAirPressure(singleForecast.getMain().getPressure());
        forecast.setForecastDate(forecastDateInstant);
        return forecast;
    }

    private LocalDateTime maptoLocalDateTime(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}


