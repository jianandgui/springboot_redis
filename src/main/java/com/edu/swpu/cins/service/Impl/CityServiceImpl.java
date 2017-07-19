package com.edu.swpu.cins.service.Impl;

import com.edu.swpu.cins.dao.CityDao;
import com.edu.swpu.cins.entity.City;
import com.edu.swpu.cins.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by muyi on 17-7-19.
 */
@Service
public class CityServiceImpl implements CityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<City> findAll() {
        return cityDao.findAllCity();
    }

    /**
     * 获取城市
     * 如果缓存存在就从缓存中取出来
     * 如果缓存中没有就去ＤＢ中取出
     *
     * @param id
     * @return
     */
    @Override
    public City findCityById(Long id) {
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();

        //缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {

            City city = operations.get(key);

            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + city.toString());
            return city;
        }

        //从DB中获取信息
        else{
            City city = cityDao.findById(id);

            //插入缓存
            operations.set(key, city, 10444444, TimeUnit.DAYS);
            LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());
            return city;
        }

    }

    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);

    }

    @Override
    public Long updateCity(City city) {

        Long ret = cityDao.updateCity(city);
        String key = "city_" + city.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {

            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + city.toString());
        }
        return ret;
    }

    @Override
    public Long deleteCity(Long id) {

        long ret = cityDao.deleteCity(id);

        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {

            redisTemplate.delete(key);
            LOGGER.info("CityServiceImpl.deleteCity() : 从缓存中删除城市 ID >> " + id);
        }
        return ret;
    }
}
