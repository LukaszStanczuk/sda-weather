package com.sda.weather.weather;

import com.sda.weather.localization.Localization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant forecastDate;
    private String temperature;
    private String humidity;
    private String windSpeed;
    private String airPressure;
    private String windDirect;
    @ManyToOne
    private Localization localizations;
}
