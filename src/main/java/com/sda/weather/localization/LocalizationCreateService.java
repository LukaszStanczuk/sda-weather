package com.sda.weather.localization;

import com.sda.weather.exception.BadrequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationCreateService {

    final LocalizationRepository localizationRepository;
    final LocalizationMapper localizationMapper;

    Localization createLocalization(LocalizationDefinition localizationDefinition) {
        if (localizationDefinition.getCity().isBlank()) {
            throw new BadrequestException("Pola nie mogą być puste");
        }
        if (localizationDefinition.getCountry().isBlank()) {
            throw new BadrequestException("Pola nie mogą być puste");
        }
        if (localizationDefinition.getLongitude() < -90 || localizationDefinition.getLongitude() > 90) {
            throw new BadrequestException("Pola nie mogą być poza zakresem");
        }
        if (localizationDefinition.getLatitude() < -180 || localizationDefinition.getLatitude() > 180) {
            throw new BadrequestException("Pola nie mogą być poza zakresem");
        }

        LocalizationDefinition localization = LocalizationDefinition.builder()
                .city(localizationDefinition.getCity())
                .country(localizationDefinition.getCountry())
                .region(localizationDefinition.getRegion())
                .latitude(localizationDefinition.getLatitude())
                .longitude(localizationDefinition.getLongitude()).build();


        return localizationRepository.save(localizationMapper.mapLocalization(localization));
    }
}
