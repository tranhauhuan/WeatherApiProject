package com.tranhuan.WeatherApiService;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtility {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtility.class);

    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        String xff = request.getHeader("X-Forwarded-For");
        if (xff != null && !xff.isEmpty()) {
            ip = xff.split(",")[0].trim();
        }

        if (ip == null || ip.isEmpty()) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }

        LOGGER.info("Client's IP Address: {}", ip);
        return ip;
    }
}
