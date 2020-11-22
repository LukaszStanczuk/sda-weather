package com.sda.weather.localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
