package meli.challenge.quality.infrastructure.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import meli.challenge.quality.domain.entities.RoomType;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.repositories.CityRepository;
import meli.challenge.quality.domain.repositories.HotelRepository;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.repositories.RoomTypeRepository;
import meli.challenge.quality.domain.repositories.UserRepository;
import meli.challenge.quality.infrastructure.RepositoriesLoader;

public class RoomTypeRepositoryImplTest {

  private RoomTypeRepository roomTypeRepository;

  @BeforeEach
  public void setUp() throws InvalidDateException, IOException {
    CityRepository cityRepository = new CityRepositoryImpl();
    HotelRepository hotelRepository = new HotelRepositoryImpl();
    RoomRepository roomRepository = new RoomRepositoryImpl();
    roomTypeRepository = new RoomTypeRepositoryImpl();
    UserRepository userRepository = new UserRepositoryImpl();
    RepositoriesLoader loader = new RepositoriesLoader(cityRepository, hotelRepository, roomRepository,
        roomTypeRepository, userRepository);

    loader.readHotels();
  }

  @Test
  @DisplayName("should find an existing room type by name")
  public void shouldFindAnExistingRoomTypeByName() {
    assertNotEquals(null, this.roomTypeRepository.findRoomTypeByName("single"));
  }

  @Test
  @DisplayName("should ignore casing when searching by name")
  public void shouldIgnoreCasingWhenSearchingByName() {
    assertNotEquals(null, this.roomTypeRepository.findRoomTypeByName("SINgle"));
  }

  @Test
  @DisplayName("should ignore accent marks when searchig by name")
  public void shouldIgnoreAccentMarksWhenSearchingByName() {
    assertNotEquals(null, this.roomTypeRepository.findRoomTypeByName("sínglé"));
  }

  @Test
  @DisplayName("should save a new room type")
  public void shouldSaveANewRoomType() {
    RoomType roomType = new RoomType("quadruple", 4);
    this.roomTypeRepository.saveRoomType(roomType);
    assertEquals(roomType, this.roomTypeRepository.findRoomTypeByName(roomType.getName()));
  }

  @Test
  @DisplayName("should return all room types")
  public void shouldReturnAllRoomTypes() {
    int initialRoomTypesCount = this.roomTypeRepository.findAllRoomTypes().size();
    RoomType roomType = new RoomType("quadruple", 4);
    this.roomTypeRepository.saveRoomType(roomType);
    assertEquals(initialRoomTypesCount + 1, this.roomTypeRepository.findAllRoomTypes().size());
  }
}
