package com.sda.weather.weather;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Data
@ConfigurationProperties("com.sda.weather.api")
class ForecastConfig {

    private String apiKey;
    private String uri;
    private String language;
    private String units;
}
