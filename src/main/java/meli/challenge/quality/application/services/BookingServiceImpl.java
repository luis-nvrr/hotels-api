package meli.challenge.quality.application.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import meli.challenge.quality.application.dtos.BookingRequest;
import meli.challenge.quality.application.dtos.BookingResponse;
import meli.challenge.quality.domain.entities.Booking;
import meli.challenge.quality.domain.entities.City;
import meli.challenge.quality.domain.entities.Hotel;
import meli.challenge.quality.domain.entities.Room;
import meli.challenge.quality.domain.entities.RoomType;
import meli.challenge.quality.domain.entities.User;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.exceptions.InvalidCityException;
import meli.challenge.quality.domain.exceptions.InvalidHotelException;
import meli.challenge.quality.domain.exceptions.InvalidRoomTypeException;
import meli.challenge.quality.domain.exceptions.InvalidUsernameException;
import meli.challenge.quality.domain.exceptions.NoAvailableRoomException;
import meli.challenge.quality.domain.repositories.BookingRepository;
import meli.challenge.quality.domain.repositories.CityRepository;
import meli.challenge.quality.domain.repositories.HotelRepository;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.repositories.RoomTypeRepository;
import meli.challenge.quality.domain.repositories.UserRepository;
import meli.challenge.quality.domain.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

  private final BookingRepository bookingRepository;
  private final UserRepository userRepository;
  private final CityRepository cityRepository;
  private final HotelRepository hotelRepository;
  private final RoomTypeRepository roomTypeRepository;
  private final RoomRepository roomRepository;
  private final String DATE_PATTERN = "dd/MM/yyyy";
  private final DateFormat formatter = new SimpleDateFormat(DATE_PATTERN);

  public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository,
      CityRepository cityRepository, HotelRepository hotelRepository, RoomTypeRepository roomTypeRepository,
      RoomRepository roomRepository) {
    this.bookingRepository = bookingRepository;
    this.userRepository = userRepository;
    this.cityRepository = cityRepository;
    this.hotelRepository = hotelRepository;
    this.roomTypeRepository = roomTypeRepository;
    this.roomRepository = roomRepository;
  }

  @Override
  public BookingResponse saveBooking(BookingRequest bookingRequest)
      throws InvalidUsernameException, InvalidDateException, InvalidCityException, InvalidHotelException,
      InvalidRoomTypeException, NoAvailableRoomException {
    User bookingUser = getUserFromBookingRequest(bookingRequest);
    Date dateFrom = formatStringToDate(getDateFromFromBookingRequest(bookingRequest));
    Date dateTo = formatStringToDate(getDateToFromBookingRequest(bookingRequest));
    validateDateRange(dateFrom, dateTo);
    City destination = getCityFromBookingRequets(bookingRequest);
    Hotel hotel = getHotelFromBookingRequest(bookingRequest);
    int peopleAmount = getPeopleAmountFromBookingRequest(bookingRequest);
    RoomType roomType = getRoomTypeFromBookingRequest(bookingRequest);

    Room room = findAvailableRoom(hotel, roomType, peopleAmount);
    Booking booking = new Booking(bookingUser, dateFrom, dateTo, destination, hotel, peopleAmount, room);
    this.bookingRepository.saveBooking(booking);

    return new BookingResponse(booking.calculateTotalAmount());
  }

  private Room findAvailableRoom(Hotel hotel, RoomType roomType, int peopleAmount)
      throws NoAvailableRoomException {
    List<Room> possibleRooms = findAvailableRoomsByHotelAndRoomType(hotel, roomType);
    return findRoomForPeopleAmount(possibleRooms, peopleAmount);
  }

  private Room findRoomForPeopleAmount(List<Room> rooms, int peopleAmount) throws NoAvailableRoomException {
    boolean existsAvailableRoom = false;
    Room availableRoom = new Room();
    for (Room room : rooms) {
      if (isRoomTypeForPeopleAmount(room, peopleAmount)) {
        existsAvailableRoom = true;
        availableRoom = room;
        break;
      }
    }

    if (!existsAvailableRoom)
      throw new NoAvailableRoomException("the amount of people is larger than the size of the room");

    return availableRoom;
  }

  private List<Room> findAvailableRoomsByHotelAndRoomType(Hotel hotel, RoomType roomType)
      throws NoAvailableRoomException {
    List<Room> availableRooms = this.roomRepository.findAvailableRoomsByHotelAndRoomType(hotel.getCode(),
        roomType.getName());
    if (availableRooms.isEmpty())
      throw new NoAvailableRoomException("there is no available room for the hotel and room type");
    return availableRooms;
  }

  private boolean isRoomTypeForPeopleAmount(Room room, int peopleAmount) {
    return room.isForLessThanEqualPeople(peopleAmount);
  }

  private RoomType getRoomTypeFromBookingRequest(BookingRequest bookingRequest) throws InvalidRoomTypeException {
    RoomType roomType = this.roomTypeRepository.findRoomTypeByName(bookingRequest.getBooking().getRoomType());
    if (roomType == null)
      throw new InvalidRoomTypeException("invalid room type");
    return roomType;
  }

  private int getPeopleAmountFromBookingRequest(BookingRequest bookingRequest) {
    return bookingRequest.getBooking().getPeopleAmount();
  }

  private Hotel getHotelFromBookingRequest(BookingRequest bookingRequest) throws InvalidHotelException {
    Hotel hotel = this.hotelRepository.findHotelByCode(bookingRequest.getBooking().getHotelCode());
    if (hotel == null)
      throw new InvalidHotelException("invalid hotel");
    return hotel;
  }

  private City getCityFromBookingRequets(BookingRequest bookingRequest) throws InvalidCityException {
    City city = this.cityRepository.findCityByName(bookingRequest.getBooking().getDestination());
    if (city == null)
      throw new InvalidCityException("invalid destination");
    return city;
  }

  private String getDateToFromBookingRequest(BookingRequest bookingRequest) {
    return bookingRequest.getBooking().getDateTo();
  }

  private String getDateFromFromBookingRequest(BookingRequest bookingRequest) {
    return bookingRequest.getBooking().getDateFrom();
  }

  private User getUserFromBookingRequest(BookingRequest bookingRequest) throws InvalidUsernameException {
    User user = this.userRepository.findUserByUsername(bookingRequest.getUsername());
    if (user == null)
      throw new InvalidUsernameException("invalid username");
    return user;
  }

  private void validateDateRange(Date dateFrom, Date dateTo) throws InvalidDateException {
    if (dateFrom.after(dateTo))
      throw new InvalidDateException("invalid date range");
  }

  private Date formatStringToDate(String stringDate) throws InvalidDateException {
    try {
      return formatter.parse(stringDate);
    } catch (ParseException e) {
      throw new InvalidDateException("invalid date format");
    }
  }

}
