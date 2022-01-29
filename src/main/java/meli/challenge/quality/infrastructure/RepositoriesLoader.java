package meli.challenge.quality.infrastructure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import meli.challenge.quality.domain.entities.City;
import meli.challenge.quality.domain.entities.Hotel;
import meli.challenge.quality.domain.entities.Room;
import meli.challenge.quality.domain.entities.RoomType;
import meli.challenge.quality.domain.entities.User;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.repositories.CityRepository;
import meli.challenge.quality.domain.repositories.HotelRepository;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.repositories.RoomTypeRepository;
import meli.challenge.quality.domain.repositories.UserRepository;
import meli.challenge.quality.domain.utils.BooleanMapper;
import meli.challenge.quality.domain.utils.CurrencyFormatter;
import meli.challenge.quality.domain.utils.DateFormatter;
import meli.challenge.quality.domain.utils.RoomTypeMapper;

@AllArgsConstructor
@Component
@Profile("!test")
public class RepositoriesLoader {
  private final CityRepository cityRepository;
  private final HotelRepository hotelRepository;
  private final RoomRepository roomRepository;
  private final RoomTypeRepository roomTypeRepository;
  private final UserRepository userRepository;

  private static final Logger logger = Logger.getLogger(RepositoriesLoader.class);

  public void readFromFile() throws InvalidDateException {
    File file = new File("src/main/resources/dbHotels.csv");
    try (BufferedReader bufferedReader = new BufferedReader(
        new FileReader(file));) {

      loadData(bufferedReader);
      loadStub();

    } catch (IOException e) {
      logger.error(e);
    }
  }

  @PostConstruct
  private void loadStub() {
    User user = new User("seba_gonzalez@unmail.com.ar");
    this.userRepository.saveUser(user);
  }

  private void loadData(BufferedReader bufferedReader) throws IOException, InvalidDateException {
    bufferedReader.readLine();
    String line = bufferedReader.readLine();

    while (line != null) {
      String[] fields = line.split(",");
      line = bufferedReader.readLine();

      String cityName = fields[2].trim();
      City city = saveCity(cityName);

      String roomTypeName = fields[3].trim();
      RoomType roomType = saveRoomType(roomTypeName);

      String hotelCode = fields[0].trim();
      String hotelName = fields[1].trim();
      Hotel hotel = saveHotel(hotelCode, hotelName, city);

      int priceByNight = CurrencyFormatter.getIntValue(fields[4].trim());
      String availableSinceString = fields[5].trim();
      Date availableSince = DateFormatter.formatStringToDate(availableSinceString);

      String availableUntilString = fields[6].trim();
      Date availableUntil = DateFormatter.formatStringToDate(availableUntilString);

      String bookedString = fields[7].trim();
      boolean booked = BooleanMapper.getBooleanFromString(bookedString);

      saveRoom(hotel, roomType, priceByNight, availableSince, availableUntil, booked);
    }
  }

  private void saveRoom(Hotel hotel, RoomType roomType, int priceByNight, Date availableSince, Date availableUntil,
      boolean booked) {
    Room newRoom = new Room(hotel, roomType, priceByNight, availableSince, availableUntil, booked);
    this.roomRepository.saveRoom(newRoom);
  }

  private City saveCity(String cityName) {
    City existingCity = this.cityRepository.findCityByName(cityName);
    if (existingCity != null)
      return existingCity;

    City newCity = new City(cityName);
    this.cityRepository.saveCity(newCity);
    return newCity;
  }

  private RoomType saveRoomType(String roomTypeName) {
    RoomType existingRoomType = this.roomTypeRepository.findRoomTypeByName(roomTypeName);
    if (existingRoomType != null)
      return existingRoomType;

    RoomType newRoomType = new RoomType(roomTypeName, RoomTypeMapper.getMaxPeopleAmount(roomTypeName));
    this.roomTypeRepository.saveRoomType(newRoomType);
    return newRoomType;
  }

  private Hotel saveHotel(String hotelCode, String hotelName, City city) {
    Hotel existingHotel = this.hotelRepository.findHotelByCode(hotelCode);
    if (existingHotel != null)
      return existingHotel;

    Hotel newHotel = new Hotel(hotelCode, hotelName, city);
    this.hotelRepository.saveHotel(newHotel);
    return newHotel;
  }
}
