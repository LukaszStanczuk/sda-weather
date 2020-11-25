package com.sda.weather.weather;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ForecastDefinition {
    String temperature;
    String humidity;
    String windSpeed;
    String airPressure;
    String windDirect;
}
