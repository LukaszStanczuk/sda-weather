package com.sda.weather.weather;

import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationDTO;
import com.sda.weather.localization.LocalizationFetchService;
import com.sda.weather.localization.LocalizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
class ForecastController {

    private final ForecastService forecastService;


    @GetMapping("/forecast")
    ResponseEntity<Forecast> getForecastByCityAndPeriod(@RequestParam("city") String locationName, @RequestParam Integer period) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(forecastService.getForecastByCityAndPeriod(locationName, period));
    }

    @GetMapping("localization/{id}/forecast")
    ResponseEntity<ForecastDto> getForecastByIdLocalization(@PathVariable Long id, @RequestParam Integer period, @RequestParam Localization localization) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(forecastService.getForecastByIDLocaliztion(id, period, localization));
    }


    // todo let's define endpoints eg.
    //  GET: /localization/{id}/forecast?period=3
    //  GET: /forecast?period=3&city=Warszawa
}
