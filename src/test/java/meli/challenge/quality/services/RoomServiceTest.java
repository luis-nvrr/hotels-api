package meli.challenge.quality.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.MockitoAnnotations.openMocks;

import meli.challenge.quality.application.dtos.RoomResponse;
import meli.challenge.quality.application.services.RoomServiceImpl;
import meli.challenge.quality.domain.entities.Room;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.services.RoomService;

public class RoomServiceTest {
    private RoomService hotelService;
    @Mock
    private RoomRepository roomRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String DATE_FORMAT = "dd/MM/yyyy";
    private final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
    private List<Room> roomsStub;
    private List<RoomResponse> roomsResponse;

    @BeforeEach
    public void setUp() throws IOException, ParseException {
        openMocks(this);
        this.hotelService = new RoomServiceImpl(roomRepository);
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.objectMapper.setDateFormat(formatter);

        this.roomsStub = Arrays.asList(objectMapper
                .readValue(new File("src/test/resources/hotels/rooms-stub.json"),
                        Room[].class));

        this.roomsResponse = Arrays.asList(objectMapper.readValue(
                new File("src/test/resources/hotels/rooms-response.json"),
                RoomResponse[].class));
    }

    @Test
    @DisplayName("Should list all rooms")
    void shouldListAllRooms() throws IOException {
        when(roomRepository.findAllAvailableRooms()).thenReturn(roomsStub);

        List<RoomResponse> result = this.hotelService.findAllAvailableRooms();

        assertEquals(objectMapper.writeValueAsString(roomsResponse), objectMapper.writeValueAsString(result));
    }

    @Test
    @DisplayName("Should find available rooms by start date and end date, and city")
    void shouldFindAvailableRoomsByStartDateAndEndDateAndCity()
            throws ParseException, IOException, InvalidDateException {
        String startDateString = "01/02/2021";
        String endDateString = "01/05/2021";
        Date startDate = formatter.parse(startDateString);
        Date endDate = formatter.parse(endDateString);
        String cityName = "Buenos Aires";

        when(roomRepository.findAvailableRoomsByStartDateAndEndDateAndCity(startDate, endDate, cityName))
                .thenReturn(roomsStub);

        List<RoomResponse> result = this.hotelService.findAvailableRoomsByStartDateAndEndDateAndCity(startDateString,
                endDateString, cityName);

        assertEquals(objectMapper.writeValueAsString(roomsResponse),
                objectMapper.writeValueAsString(result));
    }

    @Test
    @DisplayName("Should find available rooms by start date")
    void shouldFindAvailableRoomsByStartDate() throws ParseException, InvalidDateException, IOException {
        String startDateString = "01/04/2021";
        Date startDate = formatter.parse(startDateString);

        when(roomRepository.findAvailableRoomsByStartDate(startDate)).thenReturn(roomsStub);
        List<RoomResponse> result = this.hotelService.findAvailableRoomsByStartDate(startDateString);
        assertEquals(objectMapper.writeValueAsString(roomsResponse), objectMapper.writeValueAsString(result));
    }

    @Test
    @DisplayName("Should find available rooms by end date")
    void shouldFindAvailableRoomsByEndDate() throws ParseException, InvalidDateException, IOException {
        String endDateString = "01/04/2021";
        Date endDate = formatter.parse(endDateString);

        when(roomRepository.findAvailableRoomsByEndDate(endDate)).thenReturn(roomsStub);
        List<RoomResponse> result = this.hotelService.findAvailableRoomsByEndDate(endDateString);
        assertEquals(objectMapper.writeValueAsString(roomsResponse), objectMapper.writeValueAsString(result));
    }

    @Test
    @DisplayName("Should find available rooms by start date and city")
    void shouldFindAvailableRoomsByStartDateAndCity() throws ParseException, InvalidDateException, IOException {
        String startDateString = "01/04/2021";
        Date startDate = formatter.parse(startDateString);
        String cityName = "Tucumán";

        when(roomRepository.findAvailableRoomsByStartDateAndCity(startDate, cityName)).thenReturn(roomsStub);
        List<RoomResponse> result = this.hotelService.findAvailableRoomsByStartDateAndCity(startDateString, cityName);
        assertEquals(objectMapper.writeValueAsString(roomsResponse), objectMapper.writeValueAsString(result));
    }

    @Test
    @DisplayName("Should find available rooms by end date and city")
    void shouldFindAvailableRoomsByEndDateAndCity() throws ParseException, InvalidDateException, IOException {
        String endDateString = "01/04/2021";
        Date endDate = formatter.parse(endDateString);
        String cityName = "Tucumán";

        when(roomRepository.findAvailableRoomsByEndDateAndCity(endDate, cityName)).thenReturn(roomsStub);
        List<RoomResponse> result = this.hotelService.findAvailableRoomsByEndDateAndCity(endDateString, cityName);
        assertEquals(objectMapper.writeValueAsString(roomsResponse), objectMapper.writeValueAsString(result));
    }
}
