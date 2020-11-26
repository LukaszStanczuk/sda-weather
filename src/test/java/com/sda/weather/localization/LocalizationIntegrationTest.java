package com.sda.weather.localization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class LocalizationIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createLocalization_returns201StatusCodeAndSavesNewEntity() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDTO localizationDTO = new LocalizationDTO(null, "Gdansk", "Poland", "Pomorskie", 40.00, 22.00);
        String request = objectMapper.writeValueAsString(localizationDTO);
        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request);

        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        List<Localization> entries = localizationRepository.findAll();
        assertThat(entries.size()).isEqualTo(1);
        assertThat(entries.get(0)).satisfies(entry -> {
            assertThat(entry.getLongitude()).isEqualTo(40.00);
            assertThat(entry.getLatitude()).isEqualTo(22.00);
            assertThat(entry.getCity()).isEqualTo("Gdansk");
            assertThat(entry.getCountry()).isEqualTo("Poland");
            assertThat(entry.getRegion()).isEqualTo("Pomorskie");
        });
    }

    @Test
    void createLocalization_whenRegionIsBlank_returns201StatusCodeAndSavesNewEntity() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDTO localizationDTO = new LocalizationDTO(null, "Gdansk", "Poland", "   ", 40.00, 22.00);
        String request = objectMapper.writeValueAsString(localizationDTO);
        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request);

        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        List<Localization> entries = localizationRepository.findAll();
        assertThat(entries.size()).isEqualTo(1);
        assertThat(entries.get(0)).satisfies(entry -> {
            assertThat(entry.getLongitude()).isEqualTo(40.00);
            assertThat(entry.getLatitude()).isEqualTo(22.00);
            assertThat(entry.getCity()).isEqualTo("Gdansk");
            assertThat(entry.getCountry()).isEqualTo("Poland");
            assertThat(entry.getRegion()).isNull();
        });
    }
}
