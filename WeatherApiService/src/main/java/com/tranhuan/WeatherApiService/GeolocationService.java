package com.tranhuan.WeatherApiService;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.ip2location.IP2Location;
import com.ip2location.IPResult;
import com.tranhuan.WeatherApiCommon.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GeolocationService {
    private String path = "ip2locdb/IP2LOCATION-LITE-DB3.BIN";
    private static final Logger LOGGER = LoggerFactory.getLogger(GeolocationService.class);
    private IP2Location ip2Location = new IP2Location();
    public GeolocationService() {
        try {
            ip2Location.Open(path);
        } catch (IOException e) {
        LOGGER.error(e.getMessage(),e);
        }
    }

    Location getLocationByIPAddress(String ipAddress) throws GeolocationException {
        try {
            IPResult ipResult = ip2Location.IPQuery(ipAddress);
            if (!"OK".equals(ipResult.getStatus())) {
                throw new GeolocationException("Geolocation failed with status: "+ipResult.getStatus());
            }
            return new Location(ipResult.getCity(), ipResult.getRegion(), ipResult.getCountryLong(), ipResult.getCountryShort());
        } catch (IOException e) {
            throw new GeolocationException("Error querying IP database", e);
        }
    }
}
