package com.sda.weather.weather;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    Optional<Forecast> findByCityAndPeriod(String locationName, Integer period);
}
