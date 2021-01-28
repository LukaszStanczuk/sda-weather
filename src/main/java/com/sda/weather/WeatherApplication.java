package com.sda.weather;

import com.sda.weather.security.User;
import com.sda.weather.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableSwagger2
@EnableJpaAuditing
@EnableScheduling
public class WeatherApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }

    //    @Scheduled(fixedRate = 2000) co 2 sekundy pojawia sie
    @Scheduled(cron = "0 */2 * * * *")
    public void generateReport() {
        log.info("At this point, we have" + userRepository.count() + " users");
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
