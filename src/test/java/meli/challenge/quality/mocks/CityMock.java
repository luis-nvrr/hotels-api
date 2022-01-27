package meli.challenge.quality.mocks;

import java.util.Hashtable;
import java.util.Map;

import meli.challenge.quality.entities.City;

public class CityMock {
  private Map<String, City> mock;

  public CityMock() {
    mock = new Hashtable<>();
    load();
  }

  private void load() {
    City puertoIguazu = new City("Puerto Iguazú");
    City buenosAires = new City("Buenos Aires");
    City tucuman = new City("Tucumán");
    City bogota = new City("Bogotá");
    City medellin = new City("Medellín");
    City cartagena = new City("Cartagena");

    City cities[] = { puertoIguazu, buenosAires, tucuman, bogota, medellin, cartagena };

    for (City city : cities) {
      mock.put(city.getName(), city);
    }
  }

  public City get(String name) {
    return this.mock.get(name);
  }
}
