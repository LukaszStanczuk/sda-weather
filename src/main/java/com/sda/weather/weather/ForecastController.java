package com.sda.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ForecastController {
    private final ForecastService forecastService;
}
