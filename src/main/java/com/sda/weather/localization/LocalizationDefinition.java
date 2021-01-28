package com.sda.weather.localization;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LocalizationDefinition {
    String city;
    String country;
    String region;
    Double longitude;
    Double latitude;
}
