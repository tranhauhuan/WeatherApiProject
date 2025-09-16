package com.tranhuan.WeatherApiService.realtime;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.annotation.Rollback;

import com.tranhuan.WeatherApiCommon.RealtimeWeather;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class RealtimeWeatherRepositoryTest {

    @Autowired
    private RealtimeWeatherRepository repository;

    @Test
    void testUpdateRealtimeWeather() {
        String code = "LACA_USA";
        RealtimeWeather realtimeWeather = repository.findById(code).get();
        realtimeWeather.setTemperature(30);
        realtimeWeather.setHumidity(32);
        realtimeWeather.setPrecipitation(45);
        realtimeWeather.setStatus("sunny");
        realtimeWeather.setWindSpeed(20);
        realtimeWeather.setLastUpdated(new Date());
        RealtimeWeather updatedWeather = repository.save(realtimeWeather);
        assertThat(updatedWeather.getHumidity()).isEqualTo(32);
        assertThat(updatedWeather.getTemperature()).isEqualTo(30);
    }
}