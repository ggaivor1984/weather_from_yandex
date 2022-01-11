package ru.gaivor.weather.communication;


//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gaivor.weather.dto.WeatherDto;

import static ru.gaivor.weather.utils.UtilConstants.*;


import java.time.LocalDate;

@Service
public class Communication {

    private RestTemplate restTemplate = new RestTemplate();

    private static final ObjectMapper mapper = new ObjectMapper();
    private String temperatureGotByJackson;

    public WeatherDto getWeatherFromYandex() {
        WeatherDto weatherDto = new WeatherDto();
        HttpHeaders headers = new HttpHeaders();
        headers.set(KEY_NAME, TOKEN);
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        ResponseEntity<String> weatherFromYandex = restTemplate.exchange(GET_TEMP_FOR_CERTAIN_LOCATION_URL,
                HttpMethod.GET, httpEntity, String.class);


        try {
            JsonNode root = mapper.readTree(weatherFromYandex.getBody());
            JsonNode factTemp = root.path("fact");
            if (!factTemp.isMissingNode()) {
                temperatureGotByJackson = factTemp.path("temp").asText();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        weatherDto.setValue(temperatureGotByJackson);
        weatherDto.setDate(LocalDate.now());

        weatherDto.setValue(temperatureGotByJackson);
        weatherDto.setDate(LocalDate.now());

        return weatherDto;
    }
}
