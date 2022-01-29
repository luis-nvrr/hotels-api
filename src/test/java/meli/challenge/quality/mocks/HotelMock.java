package meli.challenge.quality.mocks;

import java.util.Hashtable;
import java.util.Map;

import meli.challenge.quality.domain.entities.Hotel;

public class HotelMock {
  private final Map<String, Hotel> mock;
  private final CityMock cities;

  public HotelMock(CityMock cities) {
    this.mock = new Hashtable<>();
    this.cities = cities;
    this.load();
  }

  private void load() {
    Hotel cataratas = new Hotel("CH-0002", "Cataratas Hotel", cities.get("Puerto Iguazú"));
    Hotel cataratas2 = new Hotel("CH-0003", "Cataratas Hotel 2", cities.get("Puerto Iguazú"));
    Hotel bristol = new Hotel("HB-0001", "Hotel Bristol", cities.get("Buenos Aires"));
    Hotel bristol2 = new Hotel("BH-0002", "Hotel Bristol 2", cities.get("Buenos Aires"));
    Hotel sheraton = new Hotel("SH-0002", "Sheraton", cities.get("Tucumán"));
    Hotel sheraton2 = new Hotel("SH-0001", "Sheraton 2", cities.get("Tucumán"));
    Hotel selina = new Hotel("SE-0001", "Selina", cities.get("Bogotá"));
    Hotel selina2 = new Hotel("SE-0002", "Selina 2", cities.get("Bogotá"));
    Hotel elCampin = new Hotel("EC-0003", "El Campín", cities.get("Bogotá"));
    Hotel centralPlaza = new Hotel("CP-0004", "Central Plaza", cities.get("Medellín"));
    Hotel centralPlaza2 = new Hotel("CP-0002", "Central Plaza 2", cities.get("Medellín"));
    Hotel bocagrande = new Hotel("BG-0004", "Bocagrande", cities.get("Cartagena"));

    Hotel hotels[] = { cataratas, cataratas2, bristol, bristol2, sheraton, sheraton2, selina, selina2, elCampin,
        centralPlaza, centralPlaza2, bocagrande };

    for (Hotel hotel : hotels) {
      this.mock.put(hotel.getCode(), hotel);
    }
  }

  public Hotel get(String code) {
    return this.mock.get(code);
  }
}
