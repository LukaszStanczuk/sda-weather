package com.sda.weather.localization;

import com.sda.weather.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationFetchService {
    private final LocalizationRepository localizationRepository;

    public Localization fetchLocalization(Long id) {
        return localizationRepository.findById(id).orElseThrow(() -> new NotFoundException("Localization not find " + id));
    }
}
