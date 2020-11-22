package com.sda.weather.localization;

import org.springframework.stereotype.Component;

@Component
public class LocalizationMapper {
    LocalizationDTO mapToLocalizationDto(Localization newLocalization) {
        LocalizationDTO localizationDTO = new LocalizationDTO();
        localizationDTO.setCity(newLocalization.getCity());
        localizationDTO.setCountry(newLocalization.getCountry());
        localizationDTO.setLongitude(newLocalization.getLongitude());
        localizationDTO.setLatitude(newLocalization.getLatitude());
        localizationDTO.setRegion(newLocalization.getRegion());

        return localizationDTO;
    }

    LocalizationDefinition mapToLocalisationDefinition(LocalizationDTO DTO) {

        return LocalizationDefinition.builder()
                .city(DTO.getCity())
                .longitude(DTO.getLongitude())
                .latitude(DTO.getLatitude())
                .region(DTO.getRegion())
                .country(DTO.getCountry())
                .build();
    }
}
