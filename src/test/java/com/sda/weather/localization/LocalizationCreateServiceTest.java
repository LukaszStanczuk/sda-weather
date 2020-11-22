package com.sda.weather.localization;

import com.sda.weather.exception.BadrequestException;
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

        LocalizationMapper localizationMapper = new LocalizationMapper();
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("gdansk", "Polska", "Pomorskie", 22.00, 22.00);
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());
        //when
        Localization localization = localizationCreateService
                .createLocalization(localizationDefinition);
        //then
        verify(localizationRepository).save(any(Localization.class));
    }

    @Test
    void createLocalization_whenCityIsEmpty_throwsBadRequest() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("   ", "Polska", "Pomorskie", 22.00, 22.00);
        //when
        Throwable result = catchThrowable(() -> localizationCreateService.createLocalization(localizationDefinition));
        //then
        assertThat(result).isExactlyInstanceOf(BadrequestException.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }
}
