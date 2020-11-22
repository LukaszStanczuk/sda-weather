package com.sda.weather.localization;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocalizationController {

    final LocalizationCreateService localizationCreateService;
    final LocalizationMapper localizationMapper;
    final LocalizationFetchService localizationFetchService;
    final LocalizationGetAllService localizationGetAllService;

    @PostMapping("/localization")
    ResponseEntity<LocalizationDTO> createLocalization(@RequestBody LocalizationDTO localizationDTO) {
        LocalizationDefinition localizationDefinition = LocalizationDefinition.builder()
                .country(localizationDTO.getCountry()).region(localizationDTO.getRegion())
                .city(localizationDTO.getCity()).longitude(localizationDTO.getLatitude()).latitude(localizationDTO.getLongitude()).build();

        Localization newLocalization = localizationCreateService.createLocalization(localizationDefinition);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localizationMapper.mapToLocalizationDto(newLocalization));
    }

    @GetMapping("/localization/{id}")
    LocalizationDTO getLocalization(@PathVariable Long id) {
        Localization localization = localizationFetchService.fetchLocalization(id);
        return localizationMapper.mapToLocalizationDto(localization);
    }

    @GetMapping("/localization")
    List<Localization> getAllLocalization() {
        List<Localization> localizationList = localizationGetAllService.getAll();
        return localizationList;
    }


}
