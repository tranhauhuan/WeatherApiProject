package com.tranhuan.WeatherApiService.location;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.tranhuan.WeatherApiCommon.Location;
import com.tranhuan.WeatherApiCommon.RealtimeWeather;
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

    @Test
    void testGetLocationByCodeIsNull() {
        String code = "AAAAA";
        Location locationByCode = locationRepository.getLocationByCode(code);
        assertThat(locationByCode).isNull();
    }

    @Test
    void testGetLocationByCodeIsNotNull() {
        String code = "NYC_USA";
        Location locationByCode = locationRepository.getLocationByCode(code);
        assertThat(locationByCode).isNotNull();
        assertEquals(locationByCode.getCode(), code);
    }

    @Test
    void testTrashLocationByCodeSuccess() {
        String code = "NYC_USA";
        locationRepository.trashLocationByCode(code);
        Location locationByCode = locationRepository.getLocationByCode(code);
        assertThat(locationByCode).isNull();
    }

    @Test
    void testAddRealtimeWeather() {
        String code = "LACA_USA";

        
        Location locationByCode = locationRepository.getLocationByCode(code);
        RealtimeWeather realtimeWeather = locationByCode.getRealtimeWeather();
        if (realtimeWeather==null) {
             realtimeWeather = new RealtimeWeather();
             realtimeWeather.setLocation(locationByCode);
             locationByCode.setRealtimeWeather(realtimeWeather);
        }
        realtimeWeather.setTemperature(25);
        realtimeWeather.setHumidity(30);
        realtimeWeather.setPrecipitation(40);
        realtimeWeather.setStatus("sunny");
        realtimeWeather.setWindSpeed(15);
        realtimeWeather.setLastUpdated(new Date());

        Location updatedLocation = locationRepository.save(locationByCode);

        assertThat(updatedLocation.getRealtimeWeather().getLocationCode()).isEqualTo(code);
    }
}