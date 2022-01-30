package meli.challenge.quality.infrastructure.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import meli.challenge.quality.domain.entities.Hotel;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.repositories.CityRepository;
import meli.challenge.quality.domain.repositories.HotelRepository;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.repositories.RoomTypeRepository;
import meli.challenge.quality.domain.repositories.UserRepository;
import meli.challenge.quality.infrastructure.RepositoriesLoader;

public class HotelRespositoryImplTest {
  private HotelRepository hotelRepository;

  @BeforeEach
  public void setUp() throws InvalidDateException {
    CityRepository cityRepository = new CityRepositoryImpl();
    hotelRepository = new HotelRepositoryImpl();
    RoomRepository roomRepository = new RoomRepositoryImpl();
    RoomTypeRepository roomTypeRepository = new RoomTypeRepositoryImpl();
    UserRepository userRepository = new UserRepositoryImpl();
    RepositoriesLoader loader = new RepositoriesLoader(cityRepository, hotelRepository, roomRepository,
        roomTypeRepository, userRepository);

    loader.readFromFile();
  }

  @Test
  @DisplayName("should find an existing hotel by code")
  public void shouldFindHotelByCode() {
    assertNotEquals(null, this.hotelRepository.findHotelByCode("CH-0002"));
  }

  @Test
  @DisplayName("should return null if the hotel is not found")
  public void shouldReturnNullIfHotelNotFound() {
    assertEquals(null, this.hotelRepository.findHotelByCode("ZZ-0000"));
  }

  @Test
  @DisplayName("should find all hotels")
  public void shouldFindAllHotels() {
    int initialHotelCount = this.hotelRepository.findAllHotels().size();
    Hotel newHotel = new Hotel();
    newHotel.setCode("ZZ-9999");
    this.hotelRepository.saveHotel(newHotel);
    assertEquals(initialHotelCount + 1, this.hotelRepository.findAllHotels().size());
  }

  @Test
  @DisplayName("should save a new hotel")
  public void shouldSaveANewHotel() {
    Hotel newHotel = new Hotel();
    newHotel.setCode("ZZ-9999");
    this.hotelRepository.saveHotel(newHotel);
    assertEquals(newHotel, this.hotelRepository.findHotelByCode(newHotel.getCode()));
  }

}
