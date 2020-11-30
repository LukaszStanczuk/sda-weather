package com.sda.weather.weather;

import com.sda.weather.localization.Localization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String temperature;
    private String humidity;
    private String windSpeed;
    private String airPressure;
    private String windDirect;
    @ManyToOne
    private Localization localizations;
}
