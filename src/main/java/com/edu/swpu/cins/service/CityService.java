package com.edu.swpu.cins.service;

import com.edu.swpu.cins.entity.City;

import java.util.List;

/**
 * Created by muyi on 17-7-19.
 */
public interface CityService {

    /**
     *
     * @param
     * @return
     */
    List<City> findAll();

    City findCityById(Long id);

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);

}
