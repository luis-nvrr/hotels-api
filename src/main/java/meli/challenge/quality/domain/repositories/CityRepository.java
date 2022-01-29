package meli.challenge.quality.domain.repositories;

import java.util.List;

import meli.challenge.quality.domain.entities.City;

public interface CityRepository {
  City findCityByName(String cityName);

  void saveCity(City city);

  List<City> findAllCities();
}
