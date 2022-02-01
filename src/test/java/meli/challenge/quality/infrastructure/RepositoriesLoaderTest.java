package meli.challenge.quality.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.repositories.CityRepository;
import meli.challenge.quality.domain.repositories.FlightRepository;
import meli.challenge.quality.domain.repositories.HotelRepository;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.repositories.RoomTypeRepository;
import meli.challenge.quality.domain.repositories.SeatTypeRepository;
import meli.challenge.quality.domain.repositories.UserRepository;
import meli.challenge.quality.infrastructure.repositories.CityRepositoryImpl;
import meli.challenge.quality.infrastructure.repositories.FlightRepositoryImpl;
import meli.challenge.quality.infrastructure.repositories.HotelRepositoryImpl;
import meli.challenge.quality.infrastructure.repositories.RoomRepositoryImpl;
import meli.challenge.quality.infrastructure.repositories.RoomTypeRepositoryImpl;
import meli.challenge.quality.infrastructure.repositories.SeatTypeRepositoryImpl;
import meli.challenge.quality.infrastructure.repositories.UserRepositoryImpl;

public class RepositoriesLoaderTest {

  private CityRepository cityRepository;
  private HotelRepository hotelRepository;
  private RoomRepository roomRepository;
  private RoomTypeRepository roomTypeRepository;
  private UserRepository userRepository;
  private RepositoriesLoader loader;
  private FlightRepository flightRepository;
  private SeatTypeRepository seatTypeRepository;

  @BeforeEach
  public void setUp() throws InvalidDateException, IOException {
    cityRepository = new CityRepositoryImpl();
    hotelRepository = new HotelRepositoryImpl();
    roomRepository = new RoomRepositoryImpl();
    roomTypeRepository = new RoomTypeRepositoryImpl();
    userRepository = new UserRepositoryImpl();
    loader = new RepositoriesLoader(cityRepository, hotelRepository, roomRepository,
        roomTypeRepository, userRepository);
    loader.readHotels();
  }

  @Test
  @DisplayName("Should save the room types")
  void shouldSaveTheRoomTypes() {

    assertNotEquals(null, roomTypeRepository.findRoomTypeByName("Single"));
    assertEquals(4, roomTypeRepository.findAllRoomTypes().size());
    assertEquals(null, roomTypeRepository.findRoomTypeByName("not existant"));
  }

  @Test
  @DisplayName("Should save the cities")
  void shouldSaveTheCities() {

    assertNotEquals(null, cityRepository.findCityByName("Puerto Iguazú"));
    assertEquals(6, cityRepository.findAllCities().size());
    assertEquals(null, cityRepository.findCityByName("not existant"));
  }

  @Test
  @DisplayName("Should save the hotels")
  void shouldSaveTheHotels() {

    assertNotEquals(null, this.hotelRepository.findHotelByCode("CH-0002"));
    assertEquals(12, this.hotelRepository.findAllHotels().size());
    assertEquals(null, this.hotelRepository.findHotelByCode("not existant"));
  }

  @Test
  @DisplayName("Should save the rooms")
  void shouldSaveAllRooms() {

    assertEquals(12, this.roomRepository.findAllRooms().size());
  }

  @Test
  @DisplayName("Should save the users")
  void shouldSaveAllUsers() {

    assertEquals(1, this.userRepository.findAllUsers().size());
  }

  @Test
  @DisplayName("Should save the flights")
  void shouldSaveAllFlights() throws InvalidDateException {
    flightRepository = new FlightRepositoryImpl();
    seatTypeRepository = new SeatTypeRepositoryImpl();
    loader.setFlightRepositories(flightRepository, seatTypeRepository);
    loader.readFlights();
    assertEquals(12, this.flightRepository.findAllFlights().size());
  }

  @Test
  @DisplayName("Should save all seat types")
  void shouldSaveAllSeatTypes() throws InvalidDateException {
    flightRepository = new FlightRepositoryImpl();
    seatTypeRepository = new SeatTypeRepositoryImpl();
    loader.setFlightRepositories(flightRepository, seatTypeRepository);
    loader.readFlights();
    assertEquals(2, this.seatTypeRepository.findAllSeatTypes().size());
  }
}
