package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;


@Component


public class ForecastConnection {
    private static final String API_URL = "http://api.weatherstack.com/forecast";
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();


    public ForecastConnection() {
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public ForecastDto getForecast() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL, String.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("unable to connect to server");
        }
        String responseEntityBody = responseEntity.getBody();

        ForecastDto forecastDto;
        try {
            forecastDto = objectMapper.readValue(responseEntityBody, ForecastDto.class);
            String airPressure = forecastDto.getAirPressure();
            String humidity = forecastDto.getHumidity();
            String temperature = forecastDto.getTemperature();
            String windDirect = forecastDto.getWindDirect();
            String windSpeed = forecastDto.getWindSpeed();


        } catch (JsonProcessingException e) {
            throw new RuntimeException("incorrect data reading");
        }


        return forecastDto;
    }


}

