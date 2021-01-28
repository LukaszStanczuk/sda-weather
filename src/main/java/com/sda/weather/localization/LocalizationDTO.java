package com.sda.weather.localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizationDTO {
    Long id;
    String city;
    String country;
    String region;
    Double longitude;
    Double latitude;
}
