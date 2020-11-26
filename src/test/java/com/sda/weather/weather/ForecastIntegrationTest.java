package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class ForecastIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ForecastRepository forecastRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createForecast_returns201StatusCodeAndSaveNewEntity() throws JsonProcessingException {
        //given
        forecastRepository.deleteAll();
        ForecastDto forecastDto = new ForecastDto(null, "23", "duża", "22", "44", "wschód");
        String request = objectMapper.writeValueAsString(forecastDto);
        MockHttpServletRequestBuilder post = post("/");
    }
}