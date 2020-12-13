package com.sda.weather.localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String country;
    String city;
    String region;
    Double longitude;
    Double latitude;

    public Localization(Long id, String country, String city, String region, Double longitude, Double latitude) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.region = region;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @CreatedDate
    private Instant createdDate;
    @CreatedBy
    private String createdBy;
}
