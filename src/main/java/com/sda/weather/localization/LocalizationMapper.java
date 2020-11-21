package com.sda.weather.localization;

import org.springframework.stereotype.Component;

@Component
public class LocalizationMapper {
    LocalizationDTO mapToLocalizationDto(Localization newLocalization) {
        LocalizationDTO localizationDTO = new LocalizationDTO();
        localizationDTO.setCity(newLocalization.getCity());
        localizationDTO.setCountry(newLocalization.getCountry());
        localizationDTO.setLatitude(newLocalization.getLatitude());
        localizationDTO.setLongitude(newLocalization.getLongitude());
        localizationDTO.setId(newLocalization.getId());
        return localizationDTO;
    }
}
