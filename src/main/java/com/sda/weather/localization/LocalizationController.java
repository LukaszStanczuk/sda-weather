package com.sda.weather.localization;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LocalizationController {

    final LocalizationCreateService localizationCreateService;
    final LocalizationMapper localizationMapper;
    final LocalizationFetchService localizationFetchService;
    final LocalizationGetAllService localizationGetAllService;

    @PostMapping("/localization")
    ResponseEntity<LocalizationDTO> createLocalization(@RequestBody LocalizationDTO localizationDTO) {
        LocalizationDefinition localizationDefinition = localizationMapper.mapToLocalisationDefinition(localizationDTO);    // todo move to the localizationMapper
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
    List<LocalizationDTO> getAllLocalization() {
        List<Localization> localizationList = localizationGetAllService.getAll();


        // todo use .stream().map(...) use localizationMapper.mapToLocalizationDto
        return localizationList.stream().map(l -> localizationMapper
                .mapToLocalizationDto(l))
                .collect(Collectors.toList());
    }
}
