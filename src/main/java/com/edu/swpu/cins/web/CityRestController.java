package com.edu.swpu.cins.web;

import com.edu.swpu.cins.entity.City;
import com.edu.swpu.cins.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by muyi on 17-7-19.
 */
@RestController
@RequestMapping("redisTest")
public class CityRestController {

    @Autowired
    private CityService cityService;

    @GetMapping(value = "/city/{id}")
    public City findOneCity(@PathVariable("id") Long id) {
        return cityService.findCityById(id);
    }

    @PostMapping(value = "/city")
    public void createCity(@RequestBody City city) {
        cityService.saveCity(city);
    }

    @PutMapping(value = "/city")
    public void modifyCity(@RequestBody City city) {
        cityService.updateCity(city);
    }

    @GetMapping(value = "/cities")
    public List<City> allCity(){
        return cityService.findAll();
    }

    @DeleteMapping(value = "/city/{id}")
    public void modifyCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
    }

}
