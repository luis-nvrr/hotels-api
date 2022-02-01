package meli.challenge.quality.infrastructure.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import meli.challenge.quality.domain.entities.City;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.repositories.CityRepository;
import meli.challenge.quality.domain.repositories.HotelRepository;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.repositories.RoomTypeRepository;
import meli.challenge.quality.domain.repositories.UserRepository;
import meli.challenge.quality.infrastructure.RepositoriesLoader;

public class CityRepositoryImplTest {
  private CityRepository cityRepository;

  @BeforeEach
  public void setUp() throws InvalidDateException, IOException {
    cityRepository = new CityRepositoryImpl();
    HotelRepository hotelRepository = new HotelRepositoryImpl();
    RoomRepository roomRepository = new RoomRepositoryImpl();
    RoomTypeRepository roomTypeRepository = new RoomTypeRepositoryImpl();
    UserRepository userRepository = new UserRepositoryImpl();
    RepositoriesLoader loader = new RepositoriesLoader(cityRepository, hotelRepository, roomRepository,
        roomTypeRepository, userRepository);

    loader.readHotels();
  }

  @Test
  @DisplayName("Should find an existing city by name")
  void shouldFindCityByName() {
    assertNotEquals(null, this.cityRepository.findCityByName("Buenos Aires"));
  }

  @Test
  @DisplayName("Should find an existing city by name ignoring casing")
  void shouldFindCityByNameIgnoringCasing() {
    assertNotEquals(null, this.cityRepository.findCityByName("BuenoS aiRES"));
  }

  @Test
  @DisplayName("Should find an existing city by name ignoring accent marks")
  void shouldFindCityByNameIgnoringAccentMarks() {
    assertNotEquals(null, this.cityRepository.findCityByName("Puerto iguazu"));
  }

  @Test
  @DisplayName("Should return null if a city is not existant")
  void shouldReturnNullIfNotExistant() {
    assertEquals(null, this.cityRepository.findCityByName("Test"));
  }

  @Test
  @DisplayName("Should save a city")
  void shouldSaveACity() {
    City newCity = new City("test city");
    this.cityRepository.saveCity(newCity);
    assertEquals(newCity, this.cityRepository.findCityByName(newCity.getName()));
  }

  @Test
  @DisplayName("Should return all cities")
  void shouldReturnAllCities() {
    int initialCitiesCount = this.cityRepository.findAllCities().size();
    City newCity = new City("test city");
    this.cityRepository.saveCity(newCity);
    assertEquals(initialCitiesCount + 1, this.cityRepository.findAllCities().size());
  }
}
