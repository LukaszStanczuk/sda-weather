package com.sda.weather.weather;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Data
@ConfigurationProperties("com.sda.weather.api")
public class ForecastConfig {

    String apiKey;
    String uri;
    String language;
    String units;

}
