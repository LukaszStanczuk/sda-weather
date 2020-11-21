package com.sda.weather.localization;

import com.sda.weather.exception.BadrequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationCreateService {

    final LocalizationRepository localizationRepository;

    Localization createLocalization(String city,
                                    String country,
                                    String region,
                                    Double longitude,
                                    Double latitude) {
        if (city.isEmpty()) {
            throw new BadrequestException("Pola nie mogą być puste");
        }
        if (country.isEmpty()) {
            throw new BadrequestException("Pola nie mogą być puste");
        }
        if (region.isEmpty()) {
            throw new BadrequestException("Pola nie mogą być puste");
        }
        if (longitude < -90 || longitude > 90) {
            throw new BadrequestException("Pola nie mogą być poza zakresem");
        }
        if (latitude < -180 || latitude > 180) {
            throw new BadrequestException("Pola nie mogą być poza zakresem");
        }

        Localization localization = new Localization();
        localization.setCity(city);
        localization.setCountry(country);
        localization.setLatitude(latitude);
        localization.setLongitude(longitude);
        localization.setRegion(region);

        return localizationRepository.save(localization);
    }

}
