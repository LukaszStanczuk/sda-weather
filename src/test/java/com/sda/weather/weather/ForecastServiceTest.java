package com.sda.weather.weather;

import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationFetchService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ForecastServiceTest {

    @Mock
    LocalizationFetchService localizationFetchService;
    @InjectMocks
    ForecastService forecastService;

    @Test
    public void getForecast_returnsCorrectForecast() {
        //given
        Localization localization = new Localization();
        localization.setCity("Warsaw");
        when(localizationFetchService.fetchLocalization(any())).thenReturn(localization);
        //when
        forecastService.getForecast(1L, "4");
        //then

    }
}
