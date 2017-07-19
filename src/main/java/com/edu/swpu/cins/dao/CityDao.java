package com.edu.swpu.cins.dao;

import com.edu.swpu.cins.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by muyi on 17-7-19.
 */

@Repository
@Mapper
public interface CityDao {

    List<City> findAllCity();

    City findById(Long id);

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);

}
