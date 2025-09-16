package com.tranhuan.WeatherApiService;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommonUtilityTest {

    @Test
    void testGetIPAddressFromHeader() {
        // Mock HttpServletRequest
        HttpServletRequest request = mock(HttpServletRequest.class);


        when(request.getHeader("X-Forwarded-For")).thenReturn("192.168.1.100");

        // Gọi hàm
        String ip = CommonUtility.getIPAddress(request);

        // Kiểm tra kết quả
        assertEquals("192.168.1.100", ip);
    }
}