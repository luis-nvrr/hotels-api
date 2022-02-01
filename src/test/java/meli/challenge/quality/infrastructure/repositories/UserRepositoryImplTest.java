package meli.challenge.quality.infrastructure.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import meli.challenge.quality.domain.entities.User;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.repositories.CityRepository;
import meli.challenge.quality.domain.repositories.HotelRepository;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.repositories.RoomTypeRepository;
import meli.challenge.quality.domain.repositories.UserRepository;
import meli.challenge.quality.infrastructure.RepositoriesLoader;

public class UserRepositoryImplTest {
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() throws InvalidDateException, IOException {
    CityRepository cityRepository = new CityRepositoryImpl();
    HotelRepository hotelRepository = new HotelRepositoryImpl();
    RoomRepository roomRepository = new RoomRepositoryImpl();
    RoomTypeRepository roomTypeRepository = new RoomTypeRepositoryImpl();
    userRepository = new UserRepositoryImpl();
    RepositoriesLoader loader = new RepositoriesLoader(cityRepository, hotelRepository, roomRepository,
        roomTypeRepository, userRepository);

    loader.readHotels();
  }

  @Test
  @DisplayName("should return an existing user")
  public void shouldReturnAnExistingUser() {
    assertNotEquals(null, this.userRepository.findUserByUsername("seba_gonzalez@unmail.com.ar"));
  }

  @Test
  @DisplayName("should save a new user")
  public void shouldSaveANewUser() {
    User newUser = new User("test@test.com");
    this.userRepository.saveUser(newUser);

    assertEquals(newUser, this.userRepository.findUserByUsername(newUser.getUsername()));
  }

  @Test
  @DisplayName("should ignore casing when searching by username")
  public void shouldIgnoreCasingWhenSearchingByUsername() {
    User newUser = new User("test@TEST.com");
    this.userRepository.saveUser(newUser);

    assertEquals(newUser, this.userRepository.findUserByUsername("TESt@teST.com"));
  }

  @Test
  @DisplayName("should ignore accent marks when searching by username")
  public void shouldIgnoreAccentMarksWhenSearchingByUsername() {
    User newUser = new User("tést@tést.com");
    this.userRepository.saveUser(newUser);

    assertEquals(newUser, this.userRepository.findUserByUsername("test@test.com"));
  }

  @Test
  @DisplayName("should return null if user is not found")
  public void shouldReturnNullIFUserIsNotFound() {
    assertEquals(null, this.userRepository.findUserByUsername("test"));
  }
}
