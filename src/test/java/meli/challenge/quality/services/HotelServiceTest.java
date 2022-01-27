package meli.challenge.quality.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.MockitoAnnotations.openMocks;

import meli.challenge.quality.dtos.HotelRoomResponse;
import meli.challenge.quality.entities.Room;
import meli.challenge.quality.mocks.CityMock;
import meli.challenge.quality.mocks.HotelMock;
import meli.challenge.quality.mocks.RoomMock;
import meli.challenge.quality.mocks.RoomTypeMock;
import meli.challenge.quality.repositories.RoomRepository;
import meli.challenge.quality.services.HotelServiceImpl.IHotelRoomResponseBuilder;
import meli.challenge.quality.services.HotelServiceImpl.HotelRoomResponseBuilder;

public class HotelServiceTest {
  private HotelService hotelService;
  @Mock
  private RoomRepository roomRepository;
  private final ObjectMapper objectMapper = new ObjectMapper();
  private List<HotelRoomResponse> allRoomsResponse;
  private RoomMock roomMock;
  private Logger logger = Logger.getLogger(HotelServiceTest.class);

  @BeforeEach
  public void setUp() throws IOException, ParseException {
    openMocks(this);
    this.hotelService = new HotelServiceImpl(roomRepository);
    this.objectMapper.registerModule(new JavaTimeModule());
    this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    this.allRoomsResponse = objectMapper.readValue(new File("src/test/resources/hotels/allHotelsResponse.json"),
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
    when(roomRepository.findAll()).thenReturn(this.roomMock.findAll());
    assertEquals(allRoomsResponse.size(), this.hotelService.findAllHotels().size());
  }

  @Test
  @DisplayName("Should contain all the fields expected in the response")
  void shouldMatchExpectedSerialization() throws StreamReadException, DatabindException, IOException {
    Room room = this.roomMock.get("CP-0002");
    IHotelRoomResponseBuilder responseBuilder = ((HotelServiceImpl) this.hotelService).new HotelRoomResponseBuilder(
        room);
    responseBuilder.build();
    HotelRoomResponse responseExpected = objectMapper.readValue(
        new File("src/test/resources/hotels/oneHotelResponse.json"),
        new TypeReference<>() {
        });
    assertEquals(objectMapper.writeValueAsString(responseBuilder.getResponse()),
        objectMapper.writeValueAsString(responseExpected));
  }
}
