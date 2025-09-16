package com.tranhuan.WeatherApiService;


import java.io.IOException;

import com.ip2location.IP2Location;
import com.ip2location.IPResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class IP2LocationTests {
    private String path = "ip2locdb/IP2LOCATION-LITE-DB3.BIN";

    @Test
    void testInvalidIP() throws IOException {
        String IP = "abc";
        IP2Location ip2Location = new IP2Location();
        ip2Location.Open(path);
        IPResult ipResult = ip2Location.IPQuery(IP);
        assertThat(ipResult.getStatus()).isEqualTo("INVALID_IP_ADDRESS");
        System.out.println(ipResult);
    }

    @Test
    void testValidIP1() throws IOException {
        String ipAddress = "108.30.178.78"; // New York
        IP2Location ip2Location = new IP2Location();
        ip2Location.Open(path);
        IPResult ipResult = ip2Location.IPQuery(ipAddress);
        assertThat(ipResult.getStatus()).isEqualTo("OK");
        assertThat(ipResult.getCity()).isEqualTo("New York City");
        System.out.println(ipResult);

    }
    @Test
    void testValid2() throws IOException {
        String ipAddress = "103.48.198.141"; // Delhi
        IP2Location ip2Location = new IP2Location();
        ip2Location.Open(path);
        IPResult ipResult = ip2Location.IPQuery(ipAddress);
        assertThat(ipResult.getStatus()).isEqualTo("OK");
        assertThat(ipResult.getCity()).isEqualTo("Delhi");
        System.out.println(ipResult);
    }

}
