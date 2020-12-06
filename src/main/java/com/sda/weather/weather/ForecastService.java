package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.exception.BadRequestException;
import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class ForecastService {

    private final LocalizationFetchService localizationFetchService;
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
        try {
            ForecastOpenWeather forecastOpenWeather = objectMapper.readValue(response, ForecastOpenWeather.class);
            LocalDate now = LocalDate.now();
            LocalDate forecacastDate = now.plusDays(period);
            LocalDateTime localDateTime = forecacastDate.atTime(12, 00, 00);


            forecastOpenWeather.getList()
                    .stream().map(sf -> sf.getDate()).equals(localDateTime);

//                    .map(s -> s.getWind())
//                    .map(s -> s.getSpeed())
//                    .findFirst()
//                    .orElseThrow(() -> new BadRequestException("nie ma takich danych"));
            forecastOpenWeather.getList()
                    .stream()
                    .map(s -> s.getWind())
                    .map(s -> s.getDeg())
                    .findFirst()
                    .orElseThrow(() -> new BadRequestException("nie ma takich danych"));
//            forecastOpenWeather.getList()
//                    .stream()
//                    .map(singleForecast -> singleForecast.getMain());


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Forecast();


    }

}
