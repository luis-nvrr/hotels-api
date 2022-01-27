package meli.challenge.quality.mocks;

import java.util.Hashtable;
import java.util.Map;

import meli.challenge.quality.entities.Hotel;

public class HotelMock {
  private final Map<String, Hotel> mock;
  private final CityMock cities;

  public HotelMock(CityMock cities) {
    this.mock = new Hashtable<>();
    this.cities = cities;
    this.load();
  }

  private void load() {
    Hotel cataratas = new Hotel("Cataratas Hotel", cities.get("Puerto Iguazú"));
    Hotel cataratas2 = new Hotel("Cataratas Hotel 2", cities.get("Puerto Iguazú"));
    Hotel bristol = new Hotel("Hotel Bristol", cities.get("Buenos Aires"));
    Hotel bristol2 = new Hotel("Hotel Bristol 2", cities.get("Buenos Aires"));
    Hotel sheraton = new Hotel("Sheraton", cities.get("Tucumán"));
    Hotel sheraton2 = new Hotel("Sheraton 2", cities.get("Tucumán"));
    Hotel selina = new Hotel("Selina", cities.get("Bogotá"));
    Hotel selina2 = new Hotel("Selina 2", cities.get("Bogotá"));
    Hotel elCampin = new Hotel("El Campín", cities.get("Bogotá"));
    Hotel centralPlaza = new Hotel("Central Plaza", cities.get("Medellín"));
    Hotel centralPlaza2 = new Hotel("Central Plaza 2", cities.get("Medellín"));
    Hotel bocagrande = new Hotel("Bocagrande", cities.get("Cartagena"));

    Hotel hotels[] = { cataratas, cataratas2, bristol, bristol2, sheraton, sheraton2, selina, selina2, elCampin,
        centralPlaza, centralPlaza2, bocagrande };

    for (Hotel hotel : hotels) {
      this.mock.put(hotel.getName(), hotel);
    }
  }

  public Hotel get(String name) {
    return this.mock.get(name);
  }
}
