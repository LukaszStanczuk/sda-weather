package com.sda.weather.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForecastDto {
    private Long id;
    private String temperature;
    private String humidity;
    private String windSpeed;
    private String airPressure;
    private String windDirect;


}
