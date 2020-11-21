package com.sda.weather.localization;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LocalizationController {

    final LocalizationCreateService localizationCreateService;
    final LocalizationMapper localizationMapper;
    final LocalizationFetchService localizationFetchService;

    @PostMapping("/localization")
    ResponseEntity<LocalizationDTO> createLocalization(@RequestBody LocalizationDTO localizationDTO) {
        String country = localizationDTO.getCountry();
        String region = localizationDTO.getRegion();
        String city = localizationDTO.getCity();
        Double longitude = localizationDTO.getLongitude();
        Double latitude = localizationDTO.getLatitude();
        Localization newLocalization = localizationCreateService.createLocalization(country, region, city, longitude, latitude);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localizationMapper.mapToLocalizationDto(newLocalization));
    }

    LocalizationDTO getLocalization(@PathVariable String id) {
        Localization localization = localizationFetchService.fetchLocalization(id);
        return localizationMapper.mapToLocalizationDto(localization);
    }


}
