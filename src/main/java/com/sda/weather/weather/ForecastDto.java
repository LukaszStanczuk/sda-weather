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

    private Instant forecastDate;
    private float temperature;
    private int humidity;
    private double windSpeed;
    private int airPressure;
    private double windDirect;
}
