package com.sda.weather.weather;

public class ForecastKelvinToCelsius {
    public double convertKelvintoCelcius(double value) {
        double degreeToConvert = 273.15;
        return value - degreeToConvert;
    }
}
