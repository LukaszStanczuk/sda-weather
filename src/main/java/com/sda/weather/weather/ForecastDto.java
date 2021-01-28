package com.sda.weather.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class ForecastDto {

    private long id;
    private Instant forecastDate;
    private String temperature;
    private String humidity;
    private String windSpeed;
    private String airPressure;
    private String windDirect;
}
