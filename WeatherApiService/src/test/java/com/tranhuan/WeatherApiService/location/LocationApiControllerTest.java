package com.tranhuan.WeatherApiService.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tranhuan.WeatherApiCommon.Location;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(LocationApiController.class)
class LocationApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LocationService locationService;

    @Autowired
    private ObjectMapper objectMapper;
    private static final String END_POINT_PATH = "/v1/locations";

    @Test
    void testAddShouldReturn400BadRequest() throws Exception {
        // assumptions
        Location location = new Location();
        String bodyContent = objectMapper.writeValueAsString(location);

        // executions + validations
        mockMvc.perform(post(END_POINT_PATH).contentType("application/json").content(bodyContent))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void testAddShouldReturn201Created() throws Exception {
        // assumptions
        Location location = new Location();
        location.setCode("NYC_USA");
        location.setCityName("New York City");
        location.setRegionName("New York");
        location.setCountryCode("US");
        location.setCountryName("United States of America");
        location.setEnabled(true);

        // executions
        Mockito.when(locationService.add(location)).thenReturn(location);
        String bodyContent = objectMapper.writeValueAsString(location);

        // validations
        mockMvc.perform(post(END_POINT_PATH).contentType("application/json").content(bodyContent))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.code", is("NYC_USA")))
                .andExpect(jsonPath("$.city_name", is("New York City")))
                .andExpect(header().string("Location", "/v1/locations/NYC_USA"))
                .andDo(print());
    }
}