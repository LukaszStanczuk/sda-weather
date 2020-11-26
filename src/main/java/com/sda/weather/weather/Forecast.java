package com.sda.weather.weather;

import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationDTO;
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
    Long id;
    String temperature;
    String humidity;
    String windSpeed;
    String airPressure;
    String windDirect;


    @ManyToOne()
    private Localization localizations;


}
