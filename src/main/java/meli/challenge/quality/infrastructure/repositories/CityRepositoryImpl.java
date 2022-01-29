package meli.challenge.quality.infrastructure.repositories;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.City;
import meli.challenge.quality.domain.repositories.CityRepository;
import meli.challenge.quality.domain.utils.StringNormalizer;

@Repository
public class CityRepositoryImpl implements CityRepository {
  private Map<String, City> repository;

  public CityRepositoryImpl() {
    this.repository = new Hashtable<>();
  }

  @Override
  public City findCityByName(String cityName) {
    String normalizedCityName = StringNormalizer.normalizeStringToKey(cityName);
    return this.repository.get(normalizedCityName);
  }

  @Override
  public void saveCity(City city) {
    String normalizedCityName = StringNormalizer.normalizeStringToKey(city.getName());
    this.repository.putIfAbsent(normalizedCityName, city);
  }

  @Override
  public List<City> findAllCities() {
    List<City> cities = new ArrayList<>();
    cities.addAll(repository.values());
    return cities;
  }
}
