package com.sda.weather.localization;

import org.springframework.stereotype.Component;

@Component
public class LocalizationMapper {
    LocalizationDTO mapToLocalizationDto(Localization newLocalization) {
        LocalizationDTO localizationDTO = new LocalizationDTO();
        localizationDTO.setCity(newLocalization.getCity());
        localizationDTO.setCountry(newLocalization.getCountry());

        localizationDTO.setId(newLocalization.getId());
        return localizationDTO;
    }

    Localization mapLocalization(LocalizationDefinition newLocalizationDefinition) {
        Localization localization = new Localization();
        localization.setCity(newLocalizationDefinition.getCity());
        localization.setCountry(newLocalizationDefinition.getCountry());
        localization.setRegion(newLocalizationDefinition.getRegion());
        localization.setLatitude(newLocalizationDefinition.getLatitude());
        localization.setLongitude(newLocalizationDefinition.getLongitude());
        return localization;
    }
}
