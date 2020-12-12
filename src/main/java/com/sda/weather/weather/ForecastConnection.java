package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ForecastConnection {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    String url = UriComponentsBuilder.newInstance()
            .scheme("http").host("api.openweathermap.org/data/2.5/forecast")
            .queryParam("q", cityName)
            .queryParam("appid", "3b26fcb0c4ae6a52325337d9a0f3690b")
            .queryParam("units", "metric")
            .build().toUriString();

    ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
    String response = entity.getBody();

        if(!entity.getStatusCode().

    is2xxSuccessful())

    {
        throw new RuntimeException("unable to connect to server");
    }

    ForecastOpenWeather forecastOpenWeather = null;
        try

    {
        forecastOpenWeather = objectMapper.readValue(response, ForecastOpenWeather.class);
        LocalDate now = LocalDate.now();
        LocalDate forecacastDate = now.plusDays(period);
        LocalDateTime localDateTime = forecacastDate.atTime(12, 00, 00);
        List<ForecastOpenWeather.SingleForecast> singleForecasts = forecastOpenWeather.getList();
        return singleForecasts.stream()
                .filter(sf -> maptoLocalDateTime(sf.getDate()).isEqual(localDateTime))
                .map(sf -> mapToForecast(sf))
                .findFirst();

    } catch(
    JsonProcessingException jsonProcessingException)

    {
        jsonProcessingException.printStackTrace();

    }
}
