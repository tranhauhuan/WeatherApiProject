package com.tranhuan.WeatherApiService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private Date timestamp;
    private int status;
    private String path;
    private List<String> errors =new ArrayList<>();
}
