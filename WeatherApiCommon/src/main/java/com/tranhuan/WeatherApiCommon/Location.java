package com.tranhuan.WeatherApiCommon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "locations")
public class Location {

    @Column(length = 12, nullable = false, unique = true)
    @Id
    @NotBlank
    private String code;

    @NotBlank
    @Column(length = 128, nullable = false)
    @JsonProperty("city_name")
    private String cityName;


    @JsonProperty("region_name")
    @NotNull
    private String regionName;


    @Column(length = 64, nullable = false)
    @JsonProperty("country_name")
    @NotBlank
    private String countryName;


    @Column(length = 2, nullable = false)
    @JsonProperty("country_code")
    @NotBlank
    private String countryCode;

    private boolean enabled;


    @JsonIgnore
    private boolean trashed;
}
