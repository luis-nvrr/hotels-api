package meli.challenge.quality.application.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import meli.challenge.quality.application.dtos.RoomResponse;
import meli.challenge.quality.domain.entities.Room;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.services.RoomService;

@Service
@NoArgsConstructor
public class RoomServiceImpl implements RoomService {

  private RoomRepository roomRepository;
  private final String DATE_PATTERN = "dd/MM/yyyy";
  private final DateFormat formatter = new SimpleDateFormat(DATE_PATTERN);

  public RoomServiceImpl(RoomRepository hotelRoomRepository) {
    this.roomRepository = hotelRoomRepository;
  }

  @Override
  public List<RoomResponse> findAllAvailableRooms() {
    List<Room> rooms = roomRepository.findAllAvailableRooms();
    return this.roomsListToRoomResponsesList(rooms);
  }

  @Override
  public List<RoomResponse> findAvailableRoomsByStartDateAndEndDateAndCity(String startDateString,
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
  public List<RoomResponse> findAvailableRoomsByStartDate(String startDateString) throws InvalidDateException {
    Date startDate = stringToDate(startDateString);
    List<Room> rooms = this.roomRepository.findAvailableRoomsByStartDate(startDate);
    return this.roomsListToRoomResponsesList(rooms);
  }

  @Override
  public List<RoomResponse> findAvailableRoomsByEndDate(String endDateStrig) throws InvalidDateException {
    Date endDate = stringToDate(endDateStrig);
    List<Room> rooms = this.roomRepository.findAvailableRoomsByEndDate(endDate);
    return this.roomsListToRoomResponsesList(rooms);
  }

  @Override
  public List<RoomResponse> findAvailableRoomsByStartDateAndCity(String startDateString, String cityName)
      throws InvalidDateException {
    Date startDate = stringToDate(startDateString);
    List<Room> rooms = this.roomRepository.findAvailableRoomsByStartDateAndCity(startDate, cityName);
    return this.roomsListToRoomResponsesList(rooms);
  }

  @Override
  public List<RoomResponse> findAvailableRoomsByEndDateAndCity(String endDateString, String cityName)
      throws InvalidDateException {
    Date endDate = stringToDate(endDateString);
    List<Room> rooms = this.roomRepository.findAvailableRoomsByEndDateAndCity(endDate, cityName);
    return this.roomsListToRoomResponsesList(rooms);
  }

  private List<RoomResponse> roomsListToRoomResponsesList(List<Room> rooms) {
    List<RoomResponse> roomResponses = new ArrayList<>();
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

    RoomResponse getResponse();
  }

  public class HotelRoomResponseBuilder implements IHotelRoomResponseBuilder {
    private RoomResponse response;
    private Room room;
    private final String DATE_PATTERN = "dd/MM/yyyy";
    private final DateFormat formatter = new SimpleDateFormat(DATE_PATTERN);

    public HotelRoomResponseBuilder(Room room) {
      this.response = new RoomResponse();
      this.room = room;
    }

    public void build() {
      response.setCode(room.getHotelCode());
      response.setName(room.getHotelName());
      response.setCity(room.getCityName());
      response.setRoomType(room.getRoomTypeName());
      response.setPriceByNight(String.format("$%d", room.getPriceByNight()));
      response.setAvailableSinceDate(formatter.format(room.getAvailableSince()));
      response.setAvailableUntilDate(formatter.format(room.getAvailableUntil()));
      response.setIsBooked(room.isBooked() ? "SI" : "NO");
    }

    public RoomResponse getResponse() {
      return this.response;
    }
  }
}
