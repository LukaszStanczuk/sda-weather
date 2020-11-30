package com.sda.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class ForecastController {

    private final ForecastService forecastService;

    // todo let's define endpoints eg.
    //  GET: /localization/{id}/forecast?period=3
    //  GET: /forecast?period=3&city=Warszawa
}
