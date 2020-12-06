package com.sda.weather.weather;

import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ForecastIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;
    @Autowired
    ForecastRepository forecastRepository;

    Localization savedLocalization;

    @BeforeEach
    void setUp() {
        forecastRepository.deleteAll();
        localizationRepository.deleteAll();
        Localization localization = new Localization();
        localization.setCity("Warsaw");
        localization.setCountry("Pl");
        localization.setLatitude(12.00);
        localization.setLongitude(44.55);
        savedLocalization = localizationRepository.save(localization);
    }

    @Test
    void getForecast_returns200StatusCodeAndSaveNewEntity() throws Exception {
        //given
        Long id = savedLocalization.getId();
        MockHttpServletRequestBuilder request = get("/localization/" + id + "/forecast")
                .contentType(MediaType.APPLICATION_JSON);
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void getForecast_returen400StatusCodeWhenPeriodisOver5() throws Exception {
        //given
        Long id = savedLocalization.getId();
        MockHttpServletRequestBuilder request = get("/localization/" + id + "/forecast?period=6")
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void getForecast_return400StatusCodeWhenPeriodIsUnder1() throws Exception {
        //given
        Long id = savedLocalization.getId();
        MockHttpServletRequestBuilder request = get("/localization/" + id + "/forecast?period=0")
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
