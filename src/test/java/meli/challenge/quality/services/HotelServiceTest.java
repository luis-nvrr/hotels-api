package meli.challenge.quality.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.MockitoAnnotations.openMocks;

import meli.challenge.quality.application.dtos.HotelRoomResponse;
import meli.challenge.quality.application.services.HotelServiceImpl;
import meli.challenge.quality.application.services.HotelServiceImpl.IHotelRoomResponseBuilder;
import meli.challenge.quality.domain.entities.Room;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.services.HotelService;
import meli.challenge.quality.mocks.CityMock;
import meli.challenge.quality.mocks.HotelMock;
import meli.challenge.quality.mocks.RoomMock;
import meli.challenge.quality.mocks.RoomTypeMock;

public class HotelServiceTest {
  private HotelService hotelService;
  @Mock
  private RoomRepository roomRepository;
  private final ObjectMapper objectMapper = new ObjectMapper();
  private List<HotelRoomResponse> allRoomsResponse;
  private List<HotelRoomResponse> filteredResponses;
  private RoomMock roomMock;
  private final String DATE_FORMAT = "dd/MM/yyyy";
  private final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

  @BeforeEach
  public void setUp() throws IOException, ParseException {
    openMocks(this);
    this.hotelService = new HotelServiceImpl(roomRepository);
    this.objectMapper.registerModule(new JavaTimeModule());
    this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    this.allRoomsResponse = objectMapper.readValue(new File("src/test/resources/hotels/allRoomsResponse.json"),
        new TypeReference<>() {
        });

    this.filteredResponses = objectMapper.readValue(
        new File("src/test/resources/hotels/roomsByStartDateAndEndDateAndCity.json"),
        new TypeReference<>() {
        });

    CityMock cityMock = new CityMock();
    HotelMock hotelMock = new HotelMock(cityMock);
    RoomTypeMock roomTypeMock = new RoomTypeMock();
    this.roomMock = new RoomMock(hotelMock, roomTypeMock);
  }

  @Test
  @DisplayName("Should list all rooms")
  void shouldListAllRooms() {
    when(roomRepository.findAllAvailableRooms()).thenReturn(this.roomMock.findAll());
    assertEquals(allRoomsResponse.size(), this.hotelService.findAllAvailableRooms().size());
  }

  @Test
  @DisplayName("Should contain all the fields expected in the response")
  void shouldMatchExpectedSerialization() throws StreamReadException, DatabindException, IOException {
    Room room = this.roomMock.get("CP-0002");
    IHotelRoomResponseBuilder responseBuilder = ((HotelServiceImpl) this.hotelService).new HotelRoomResponseBuilder(
        room);
    responseBuilder.build();
    HotelRoomResponse responseExpected = objectMapper.readValue(
        new File("src/test/resources/hotels/exampleRoomResponse.json"),
        new TypeReference<>() {
        });
    assertEquals(objectMapper.writeValueAsString(responseBuilder.getResponse()),
        objectMapper.writeValueAsString(responseExpected));
  }

  @Test
  @DisplayName("Should find available rooms by start date and end date, and city")
  void shouldFindAvailableRoomsByStartDateAndEndDateAndCity() throws ParseException, InvalidDateException {
    String startDateString = "01/02/2021";
    String endDateString = "01/05/2021";
    Date startDate = formatter.parse(startDateString);
    Date endDate = formatter.parse(endDateString);
    String cityName = "Buenos Aires";

    when(roomRepository.findAvailableRoomsByStartDateAndEndDateAndCity(startDate, endDate, cityName))
        .thenReturn(roomMock.findRoomsInBuenosAiresFromFirstFebruaryToFirstMay());

    assertEquals(filteredResponses.size(),
        this.hotelService.findAvailableRoomsByStartDateAndEndDateAndCity(startDateString, endDateString, cityName)
            .size());
  }

  @Test
  @DisplayName("Should find available rooms by start date")
  void shouldFindAvailableRoomsByStartDate() throws ParseException, InvalidDateException {
    String startDateString = "01/04/2021";
    Date startDate = formatter.parse(startDateString);

    when(roomRepository.findAvailableRoomsByStartDate(startDate))
        .thenReturn(roomMock.findAvailableRoomsFromFirstApril());

    assertEquals(2, this.hotelService.findAvailableRoomsByStartDate(startDateString).size());
  }

  @Test
  @DisplayName("Should find available rooms by end date")
  void shouldFindAvailableRoomsByEndDate() throws ParseException, InvalidDateException {
    String endDateString = "01/04/2021";
    Date endDate = formatter.parse(endDateString);

    when(roomRepository.findAvailableRoomsByEndDate(
        endDate))
            .thenReturn(roomMock.findAvailableRoomsUntilFirstMarch());

    assertEquals(1, this.hotelService.findAvailableRoomsByEndDate(endDateString).size());
  }

  @Test
  @DisplayName("Should find available rooms by start date and city")
  void shouldFindAvailableRoomsByStartDateAndCity() throws ParseException, InvalidDateException {
    String startDateString = "01/04/2021";
    Date startDate = formatter.parse(startDateString);
    String cityName = "Tucumán";

    when(roomRepository.findAvailableRoomsByStartDateAndCity(startDate, cityName))
        .thenReturn(roomMock.findAvailableRoomsFromFirstAprilInTucuman());

    assertEquals(1, this.hotelService.findAvailableRoomsByStartDateAndCity(startDateString, cityName).size());
  }

  @Test
  @DisplayName("Should find available rooms by end date and city")
  void shouldFindAvailableRoomsByEndDateAndCity() throws ParseException, InvalidDateException {
    String endDateString = "01/04/2021";
    Date endDate = formatter.parse(endDateString);
    String cityName = "Tucumán";

    when(roomRepository.findAvailableRoomsByEndDateAndCity(
        endDate, cityName))
            .thenReturn(roomMock.findAvailableRoomsUntilFirstMarchInTucuman());

    assertEquals(1, this.hotelService.findAvailableRoomsByEndDateAndCity(endDateString, cityName).size());
  }
}
