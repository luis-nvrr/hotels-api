package meli.challenge.quality.application.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import meli.challenge.quality.application.dtos.HotelRoomResponse;
import meli.challenge.quality.domain.entities.Room;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.services.HotelService;

@Service
@NoArgsConstructor
public class HotelServiceImpl implements HotelService {

  private RoomRepository roomRepository;
  private final String DATE_PATTERN = "dd/MM/yyyy";
  private final DateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
  private final Logger logger = Logger.getLogger(HotelServiceImpl.class);

  public HotelServiceImpl(RoomRepository hotelRoomRepository) {
    this.roomRepository = hotelRoomRepository;
  }

  @Override
  public List<HotelRoomResponse> findAllAvailableRooms() {
    List<Room> rooms = roomRepository.findAllAvailableRooms();
    return this.roomsListToRoomResponsesList(rooms);
  }

  @Override
  public List<HotelRoomResponse> findAvailableRoomsByStartDateAndEndDateAndCity(String startDateString,
      String endDateString,
      String cityName) throws InvalidDateException {
    Date startDate = stringToDate(startDateString);
    Date endDate = stringToDate(endDateString);

    if (startDate.after(endDate)) {
      throw new InvalidDateException("The date range is invalid");
    }

    List<Room> rooms = this.roomRepository.findAvailableRoomsByStartDateAndEndDateAndCity(startDate, endDate, cityName);
    return this.roomsListToRoomResponsesList(rooms);
  }

  @Override
  public List<HotelRoomResponse> findAvailableRoomsByStartDate(String startDateString) throws InvalidDateException {
    Date startDate = stringToDate(startDateString);
    List<Room> rooms = this.roomRepository.findAvailableRoomsByStartDate(startDate);
    return this.roomsListToRoomResponsesList(rooms);
  }

  @Override
  public List<HotelRoomResponse> findAvailableRoomsByEndDate(String endDateStrig) throws InvalidDateException {
    Date endDate = stringToDate(endDateStrig);
    List<Room> rooms = this.roomRepository.findAvailableRoomsByEndDate(endDate);
    return this.roomsListToRoomResponsesList(rooms);
  }

  @Override
  public List<HotelRoomResponse> findAvailableRoomsByStartDateAndCity(String startDateString, String cityName)
      throws InvalidDateException {
    Date startDate = stringToDate(startDateString);
    List<Room> rooms = this.roomRepository.findAvailableRoomsByStartDateAndCity(startDate, cityName);
    return this.roomsListToRoomResponsesList(rooms);
  }

  @Override
  public List<HotelRoomResponse> findAvailableRoomsByEndDateAndCity(String endDateString, String cityName)
      throws InvalidDateException {
    Date endDate = stringToDate(endDateString);
    List<Room> rooms = this.roomRepository.findAvailableRoomsByEndDateAndCity(endDate, cityName);
    return this.roomsListToRoomResponsesList(rooms);
  }

  private List<HotelRoomResponse> roomsListToRoomResponsesList(List<Room> rooms) {
    List<HotelRoomResponse> roomResponses = new ArrayList<>();
    for (Room room : rooms) {
      IHotelRoomResponseBuilder builder = new HotelRoomResponseBuilder(room);
      builder.build();
      roomResponses.add(builder.getResponse());
    }
    return roomResponses;
  }

  private Date stringToDate(String stringDate) throws InvalidDateException {
    try {
      return formatter.parse(stringDate);
    } catch (ParseException e) {
      throw new InvalidDateException("The date range is invalid");
    }
  }

  public interface IHotelRoomResponseBuilder {
    void build();

    HotelRoomResponse getResponse();
  }

  public class HotelRoomResponseBuilder implements IHotelRoomResponseBuilder {
    private HotelRoomResponse response;
    private Room room;
    private final String DATE_PATTERN = "dd/MM/yyyy";
    private final DateFormat formatter = new SimpleDateFormat(DATE_PATTERN);

    public HotelRoomResponseBuilder(Room room) {
      this.response = new HotelRoomResponse();
      this.room = room;
    }

    public void build() {
      response.setCode(room.getCode());
      response.setName(room.getHotelName());
      response.setCity(room.getCityName());
      response.setRoomType(room.getRoomTypeName());
      response.setPriceByNight(String.format("$%d", room.getPriceByNight()));
      response.setAvailableSinceDate(formatter.format(room.getAvailableSince()));
      response.setAvailableUntilDate(formatter.format(room.getAvailableUntil()));
      response.setIsBooked(room.isBooked() ? "SI" : "NO");
    }

    public HotelRoomResponse getResponse() {
      return this.response;
    }
  }
}
