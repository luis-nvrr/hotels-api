package meli.challenge.quality.domain.repositories;

import meli.challenge.quality.domain.entities.City;

public interface CityRepository {
  City findCityByName(String cityName);
}
