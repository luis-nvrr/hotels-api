package meli.challenge.quality.infrastructure.adapters;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.City;
import meli.challenge.quality.domain.repositories.CityRepository;

@Repository
public class SqlCityAdapter implements CityRepository {

  @Override
  public City findCityByName(String cityName) {
    // TODO Auto-generated method stub
    return null;
  }

}
