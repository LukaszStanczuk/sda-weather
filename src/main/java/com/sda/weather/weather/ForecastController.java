package com.sda.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
class ForecastController {

    private final ForecastService forecastService;
    private final ForecastMapper forecastMapper;


//    @GetMapping("/forecast")
//    ResponseEntity<Forecast> getForecastByCityAndPeriod(@RequestParam("city") String locationName, @RequestParam String period) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(forecastService.getForecast());
//    }

    @GetMapping("localization/{id}/forecast")
    ForecastDto getForecastByIdLocalization(@PathVariable Long id, @RequestParam(defaultValue = "1") @Min(1) @Max(5) Integer period) {
        return forecastMapper.mapToForecastDto(forecastService.getForecast(id, period));
    }
}
