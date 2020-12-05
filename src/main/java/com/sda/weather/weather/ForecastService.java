package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
class ForecastService {

    private final LocalizationFetchService localizationFetchService;
    private final ObjectMapper objectMapper;
    RestTemplate restTemplate = new RestTemplate();

    Forecast getForecast(Long id, String period) {
        Localization localization = localizationFetchService.fetchLocalization(id);
        String cityName = localization.getCity();

        String url = UriComponentsBuilder.newInstance()
                .scheme("http").host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", cityName)
                .queryParam("appid", "3b26fcb0c4ae6a52325337d9a0f3690b").build().toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String response = entity.getBody();

        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("unable to connect to server");
        }
        try {
            ForecastOpenWeather forecastOpenWeather = objectMapper.readValue(response, ForecastOpenWeather.class);
            System.out.println(forecastOpenWeather.getList().get(1).getDate());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Forecast();


    }

}
