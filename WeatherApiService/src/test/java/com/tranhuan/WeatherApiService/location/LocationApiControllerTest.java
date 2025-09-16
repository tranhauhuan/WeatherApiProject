package com.tranhuan.WeatherApiService.location;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tranhuan.WeatherApiCommon.Location;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    void testGetShouldReturn204NoContent() throws Exception {
        // assumptions
        Mockito.when(locationService.getNonTrashedLocations()).thenReturn(Collections.emptyList());

        // validations
        mockMvc.perform(get(END_POINT_PATH))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void testGetShouldReturn200Ok() throws Exception {
        // assumptions
        Location location1 = new Location();
        location1.setCode("NYC_USA");
        location1.setCityName("New York City");
        location1.setRegionName("New York");
        location1.setCountryCode("US");
        location1.setCountryName("United States of America");
        location1.setEnabled(true);

        Location location2 = new Location();
        location2.setCode("LACA_USA");
        location2.setCityName("Los Angeles");
        location2.setRegionName("California");
        location2.setCountryCode("US");
        location2.setCountryName("United States of America");
        location2.setEnabled(true);

        Mockito.when(locationService.getNonTrashedLocations()).thenReturn(List.of(location1, location2));

        // validations
        mockMvc.perform(get(END_POINT_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].code", is("NYC_USA")))
                .andExpect(jsonPath("$[0].city_name", is("New York City")))
                .andExpect(jsonPath("$[1].code", is("LACA_USA")))
                .andExpect(jsonPath("$[1].city_name", is("Los Angeles")))
                .andDo(print());

    }

    @Test
    void testGetLocationShouldReturn404NotFound() throws Exception {
        String code = "AAAAAAAAA";
        String requestURI = END_POINT_PATH + "/" + code;

        mockMvc.perform(get(requestURI))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testGetLocationShouldReturn200Oke() throws Exception {
        String code = "NYC_USA";
        Location location = new Location();
        location.setCode(code);
        location.setCityName("New York City");
        location.setRegionName("New York");
        location.setCountryCode("US");
        location.setCountryName("United States of America");
        location.setEnabled(true);
        String requestURI = END_POINT_PATH + "/" + code;

        Mockito.when(locationService.getLocationByCode(code)).thenReturn(location);
        mockMvc.perform(get(requestURI))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.code",is(code)))
                .andExpect(jsonPath("$.city_name",is("New York City")))
                .andDo(print());
    }
}