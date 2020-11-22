package com.sda.weather.localization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocalizationGetAllService {
    private final LocalizationRepository localizationRepository;

    public List<Localization> getAll() {
        return localizationRepository.findAll();
    }
}
