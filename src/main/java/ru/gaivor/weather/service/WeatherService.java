package ru.gaivor.weather.service;

import ru.gaivor.weather.entity.Weather;


public interface WeatherService {
    String getLatestTemperatureFromDB();

    void save(Weather weather);
}
