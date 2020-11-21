package com.sda.weather.localization;

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
        if (city.isEmpty() && country.isEmpty() && region.isEmpty() && region.isEmpty() && longitude < 0 && latitude < 0) {
//            throw new BadrequestException("Pola nie mogą być pustę a wartości poniżej 0"); // todo
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
