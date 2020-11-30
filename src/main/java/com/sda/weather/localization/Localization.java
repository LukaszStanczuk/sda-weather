package com.sda.weather.localization;

import com.sda.weather.weather.Forecast;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String country;
    String city;
    String region;
    Double longitude;
    Double latitude;

    @OneToMany(mappedBy = "localizations")
    private List<Forecast> forecasts;           // todo is it necessary?
}
