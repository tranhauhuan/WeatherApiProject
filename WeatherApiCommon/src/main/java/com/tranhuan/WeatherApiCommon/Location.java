package com.tranhuan.WeatherApiCommon;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Entity
@Table(name = "locations")
public class Location {

    @Column(length = 12, nullable = false, unique = true)
    @Id
    private String code;
    @Column(length = 128, nullable = false)
    private String cityName;
    @Column(length = 128)
    private String regionName;
    @Column(length = 64, nullable = false)
    private String countryName;
    @Column(length = 2, nullable = false)
    private String countryCode;
    private boolean enabled;
    private boolean trashed;

    public Location() {
    }
}
