package com.sda.weather;

import com.sda.weather.security.User;
import com.sda.weather.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
@RequiredArgsConstructor
public class WeatherApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        User luk = new User();
        luk.setUsername("luk");
        luk.setPassword(passwordEncoder.encode("luk"));
        luk.setAuthorities(Collections.singletonList(() -> "ROLE_USER"));
        userRepository.save(luk);

        User admin = new User();
        luk.setUsername("admin");
        luk.setPassword(passwordEncoder.encode("admin"));
        luk.setAuthorities(Collections.singletonList(() -> "ROLE_ADMIN"));
        userRepository.save(admin);

    }
}
