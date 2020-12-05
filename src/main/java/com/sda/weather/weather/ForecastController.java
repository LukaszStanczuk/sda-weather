package com.sda.weather.weather;

import com.sda.weather.localization.Localization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class ForecastController {

    private final ForecastService forecastService;
    private final ForecastMapper forecastMapper;


//    @GetMapping("/forecast")
//    ResponseEntity<Forecast> getForecastByCityAndPeriod(@RequestParam("city") String locationName, @RequestParam String period) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(forecastService.getForecast());
//    }

    @GetMapping("localization/{id}/forecast")
    ResponseEntity<ForecastDto> getForecastByIdLocalization(@PathVariable Long id, @RequestParam(defaultValue = "1") String period) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(forecastMapper.mapToForecastDto(forecastService.getForecast(id, period)));
    }


}
