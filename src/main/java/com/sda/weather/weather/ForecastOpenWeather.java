package com.sda.weather.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastOpenWeather {

    private List<SingleForecast> list;

    @Data
    public static class SingleForecast {
        private Wind wind;
        private Main main;
        @JsonProperty("dt_txt")
        private String date;
    }

    @Data
    public static class Wind {
        private double speed;
        private int deg;
    }

    @Data
    private static class Main {
        private double temp;
        private int pressure;
        private int humidity;
    }
}
