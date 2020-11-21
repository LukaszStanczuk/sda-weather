package com.sda.weather.localization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LocalizationController {

    final LocalizationCreateService localizationCreateService;
//    final LocalizationMapper localizationMapper;

    @PostMapping("/localization")
    ResponseEntity<LocalizationDTO> createLocalization(@RequestBody LocalizationDTO localizationDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null); // todo
    }

}
