package meli.challenge.quality.infrastructure.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import meli.challenge.quality.domain.entities.Hotel;
import meli.challenge.quality.domain.entities.Room;
import meli.challenge.quality.domain.entities.RoomType;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.repositories.CityRepository;
import meli.challenge.quality.domain.repositories.HotelRepository;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.repositories.RoomTypeRepository;
import meli.challenge.quality.domain.repositories.UserRepository;
import meli.challenge.quality.domain.utils.DateFormatter;
import meli.challenge.quality.infrastructure.RepositoriesLoader;

public class RoomRepositoryImplTest {
  private RoomRepository roomRepository;

  @BeforeEach
  public void setUp() throws InvalidDateException {
    CityRepository cityRepository = new CityRepositoryImpl();
    HotelRepository hotelRepository = new HotelRepositoryImpl();
    roomRepository = new RoomRepositoryImpl();
    RoomTypeRepository roomTypeRepository = new RoomTypeRepositoryImpl();
    UserRepository userRepository = new UserRepositoryImpl();
    RepositoriesLoader loader = new RepositoriesLoader(cityRepository, hotelRepository, roomRepository,
        roomTypeRepository, userRepository);

    loader.readFromFile();
  }

  @Test
  @DisplayName("should find all existing rooms")
  public void shouldFindAllExistingRooms() {
    assertEquals(12, this.roomRepository.findAllRooms().size());
  }

  @Test
  @DisplayName("should find available rooms by start date, and end date, and city")
  public void shouldFindAvailableRoomsByStartDateAndEndDateAndCity() throws InvalidDateException {
    String startDateString = "10/02/2021";
    String endDateString = "19/03/2021";
    Date startDate = DateFormatter.formatStringToDate(startDateString);
    Date endDate = DateFormatter.formatStringToDate(endDateString);
    String cityName = "Buenos Aires";

    assertEquals(1,
        this.roomRepository.findAvailableRoomsByStartDateAndEndDateAndCity(startDate, endDate, cityName).size());
  }

  @Test
  @DisplayName("should return an empty list if not found searching by start date, and end date, and city")
  public void shouldReturnEmptyListIfNotFoundSearchingByStartDateAndEndDateAndCity() throws InvalidDateException {
    String startDateString = "09/02/2021";
    String endDateString = "20/03/2021";
    Date startDate = DateFormatter.formatStringToDate(startDateString);
    Date endDate = DateFormatter.formatStringToDate(endDateString);
    String cityName = "Buenos Aires";

    assertEquals(true,
        this.roomRepository.findAvailableRoomsByStartDateAndEndDateAndCity(startDate, endDate, cityName).isEmpty());
  }

  @Test
  @DisplayName("should return all available rooms")
  public void shouldReturnAllAvailableRooms() {
    Room room = new Room(new Hotel(), new RoomType(), 1, new Date(), new Date(), true);
    this.roomRepository.saveRoom(room);
    assertEquals(12, this.roomRepository.findAllAvailableRooms().size());
  }

  @Test
  @DisplayName("should save a new room")
  public void shouldSaveANewRoom() {
    int roomCount = this.roomRepository.findAllRooms().size();
    Room room = new Room(new Hotel(), new RoomType(), 1, new Date(), new Date(), true);
    this.roomRepository.saveRoom(room);
    assertEquals(roomCount + 1, this.roomRepository.findAllRooms().size());
  }

  @Test
  @DisplayName("should find available rooms by start date")
  public void shouldFindAvailableRoomsByStartDate() throws InvalidDateException {
    String startDateString = "02/01/2021";
    Date startDate = DateFormatter.formatStringToDate(startDateString);

    assertEquals(1, this.roomRepository.findAvailableRoomsByStartDate(startDate).size());
  }

  @Test
  @DisplayName("should find available rooms by end date")
  public void shouldFindAvailableRoomsByEndDate() throws InvalidDateException {
    String endDateString = "23/11/2021";
    Date endDate = DateFormatter.formatStringToDate(endDateString);

    assertEquals(1, this.roomRepository.findAvailableRoomsByEndDate(endDate).size());
  }

  @Test
  @DisplayName("should find available rooms by start date and city")
  public void shouldFindAvailableRoomsByStartDateAndCity() throws InvalidDateException {
    String startDateString = "23/01/2021";
    String cityName = "Bogotá";
    Date startDate = DateFormatter.formatStringToDate(startDateString);

    assertEquals(2, this.roomRepository.findAvailableRoomsByStartDateAndCity(startDate, cityName).size());
  }

  @Test
  @DisplayName("should find available rooms by end date and city")
  public void shouldFindAvailableRoomsByEndDateAndCity() throws InvalidDateException {
    String endDateString = "14/10/2021";
    String cityName = "Bogotá";
    Date endDate = DateFormatter.formatStringToDate(endDateString);

    assertEquals(2, this.roomRepository.findAvailableRoomsByEndDateAndCity(endDate, cityName).size());
  }

  @Test
  @DisplayName("should find available rooms by hotel and room type")
  public void shouldFindAvailableRoomsByHotelAndRoomType() {
    String hotelCode = "CH-0002";
    String roomTypeName = "doble";

    assertEquals(1, this.roomRepository.findAvailableRoomsByHotelAndRoomType(hotelCode, roomTypeName).size());
  }
}
