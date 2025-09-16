package com.tranhuan.WeatherApiService.realtime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranhuan.WeatherApiCommon.RealtimeWeather;

public interface RealtimeWeatherRepository extends JpaRepository<RealtimeWeather, String> {
}
