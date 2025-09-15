package com.tranhuan.WeatherApiService.location;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranhuan.WeatherApiCommon.Location;


public interface LocationRepository extends JpaRepository<Location, String> {
}
