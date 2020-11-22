package com.sda.weather.localization;

import com.sda.weather.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocalizationCreateServiceTest {

    @Mock
    LocalizationRepository localizationRepository;
    @InjectMocks
    LocalizationCreateService localizationCreateService;

    @Test
    void createLocalization_callsLocalizationRepository() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("Gdansk", "Polska", "Pomorskie", 22.00, 22.00);
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());
        //when
        localizationCreateService.createLocalization(localizationDefinition);
        //then
        verify(localizationRepository).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenRegionIsEmpty_callsLocalizationRepository() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("Gdansk", "Polska", "", 22.00, 22.00);
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());
        //when
        localizationCreateService.createLocalization(localizationDefinition);
        //then
        verify(localizationRepository).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenNameIsBlank_throwsBadRequest() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition(" ", "Polska", "Pomorskie", 22.00, 22.00);
        //when
        Throwable result = catchThrowable(() -> localizationCreateService.createLocalization(localizationDefinition));
        //then
        assertThat(result).isExactlyInstanceOf(BadRequestException.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenCountryIsBlank_throwsBadRequest() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("Gdansk", " ", "Pomorskie", 22.00, 22.00);
        //when
        Throwable result = catchThrowable(() -> localizationCreateService.createLocalization(localizationDefinition));
        //then
        assertThat(result).isExactlyInstanceOf(BadRequestException.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenLongitudeIsOver180_throwsBadRequest() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("Gdansk", "Poland", "Pomorskie", 181.00, 22.00);
        //when
        Throwable result = catchThrowable(() -> localizationCreateService.createLocalization(localizationDefinition));
        //then
        assertThat(result).isExactlyInstanceOf(BadRequestException.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenLongitudeIsBelow180Negative_throwsBadRequest() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("Gdansk", "Poland", "Pomorskie", -181.00, 22.00);
        //when
        Throwable result = catchThrowable(() -> localizationCreateService.createLocalization(localizationDefinition));
        //then
        assertThat(result).isExactlyInstanceOf(BadRequestException.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenLatitudeIsOver90_throwsBadRequest() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("Gdansk", "Poland", "Pomorskie", 180.00, 91.00);
        //when
        Throwable result = catchThrowable(() -> localizationCreateService.createLocalization(localizationDefinition));
        //then
        assertThat(result).isExactlyInstanceOf(BadRequestException.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenLatitudeIsOver90Negative_throwsBadRequest() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("Gdansk", "Poland", "Pomorskie", -180.00, -90.00);
        //when
        Throwable result = catchThrowable(() -> localizationCreateService.createLocalization(localizationDefinition));
        //then
        assertThat(result).isExactlyInstanceOf(BadRequestException.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }
}
