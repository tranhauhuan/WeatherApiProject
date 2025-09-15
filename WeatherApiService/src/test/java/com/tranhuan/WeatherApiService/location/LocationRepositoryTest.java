package com.tranhuan.WeatherApiService.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.tranhuan.WeatherApiCommon.Location;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void testAddSuccess() {
        //assumptions
        Location location = new Location();
        location.setCode("NYC_USA");
        location.setCityName("New York City");
        location.setRegionName("New York");
        location.setCountryCode("US");
        location.setCountryName("United States of America");
        location.setEnabled(true);

        //executions
        Location savedLocation = locationRepository.save(location);

        //validations
        assertThat(savedLocation).isNotNull();
        assertThat(savedLocation.getCode()).isEqualTo("NYC_USA");
    }

    @Test
    void  testGetNonTrashedLocationsSuccess() {
        List<Location> nonTrashedLocations = locationRepository.getNonTrashedLocations();
        assertThat(nonTrashedLocations).isNotEmpty();
        nonTrashedLocations.forEach(System.out::println);
    }
}