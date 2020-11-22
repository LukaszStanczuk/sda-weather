package com.sda.weather.localization;

import com.sda.weather.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationCreateService {

    final LocalizationRepository localizationRepository;

    Localization createLocalization(LocalizationDefinition localizationDefinition) {
        if (localizationDefinition.getCity().isBlank()) {
            throw new BadRequestException("Pola nie mogą być puste");
        }
        if (localizationDefinition.getCountry().isBlank()) {
            throw new BadRequestException("Pola nie mogą być puste");
        }
        if (localizationDefinition.getLongitude() < -90 || localizationDefinition.getLongitude() > 90) {
            throw new BadRequestException("Pola nie mogą być poza zakresem");
        }
        if (localizationDefinition.getLatitude() < -180 || localizationDefinition.getLatitude() > 180) {
            throw new BadRequestException("Pola nie mogą być poza zakresem");
        }

        Localization localization = new Localization();
        localization.setCity(localizationDefinition.getCity());
        localization.setCountry(localizationDefinition.getCountry());
        localization.setRegion(localizationDefinition.getRegion());     // todo add a check
        localization.setLatitude(localizationDefinition.getLatitude());
        localization.setLongitude(localizationDefinition.getLongitude());

        return localizationRepository.save(localization);
    }
}
