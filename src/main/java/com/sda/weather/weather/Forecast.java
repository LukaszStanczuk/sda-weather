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
    private float temperature;
    private int humidity;
    private double windSpeed;
    private int airPressure;
    private double windDirect;
    @ManyToOne
    private Localization localizations;
}
