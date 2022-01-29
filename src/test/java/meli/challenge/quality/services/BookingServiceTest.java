package meli.challenge.quality.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import meli.challenge.quality.application.dtos.BookingRequest;
import meli.challenge.quality.application.dtos.BookingResponse;
import meli.challenge.quality.application.services.BookingServiceImpl;
import meli.challenge.quality.domain.entities.City;
import meli.challenge.quality.domain.entities.Hotel;
import meli.challenge.quality.domain.entities.Room;
import meli.challenge.quality.domain.entities.RoomType;
import meli.challenge.quality.domain.entities.User;
import meli.challenge.quality.domain.exceptions.InvalidCityException;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
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

public class BookingServiceTest {
        private BookingService bookingService;
        @Mock
        private BookingRepository bookingRepository;
        @Mock
        private UserRepository userRepository;
        @Mock
        private CityRepository cityRepository;
        @Mock
        private HotelRepository hotelRepository;
        @Mock
        private RoomTypeRepository roomTypeRepository;
        @Mock
        private RoomRepository roomRepository;

        private final ObjectMapper objectMapper = new ObjectMapper();
        private BookingRequest bookingRequest;
        private BookingResponse bookingResponse;
        private User userStub;
        private City cityStub;
        private Hotel hotelStub;
        private RoomType roomTypesStub;
        private List<Room> roomStub;
        private final String DATE_FORMAT = "dd/MM/yyyy";
        private final DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        @BeforeEach
        public void setUp() throws StreamReadException, DatabindException, IOException, ParseException {
                openMocks(this);
                this.objectMapper.registerModule(new JavaTimeModule());
                this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                this.bookingRequest = objectMapper.readValue(new File("src/test/resources/hotels/booking-request.json"),
                                BookingRequest.class);

                this.bookingService = new BookingServiceImpl(bookingRepository, userRepository, cityRepository,
                                hotelRepository, roomTypeRepository, roomRepository);

                this.objectMapper.setDateFormat(formatter);

                this.bookingResponse = objectMapper.readValue(
                                new File("src/test/resources/hotels/booking-response.json"),
                                BookingResponse.class);

                this.userStub = objectMapper.readValue(
                                new File("src/test/resources/hotels/user-stub.json"),
                                User.class);
                this.cityStub = objectMapper.readValue(
                                new File("src/test/resources/hotels/city-stub.json"),
                                City.class);
                this.hotelStub = objectMapper.readValue(
                                new File("src/test/resources/hotels/hotel-stub.json"),
                                Hotel.class);
                this.roomTypesStub = objectMapper.readValue(
                                new File("src/test/resources/hotels/room-type-stub.json"),
                                RoomType.class);
                this.roomStub = Arrays.asList(objectMapper.readValue(
                                new File("src/test/resources/hotels/available-rooms-stub.json"),
                                Room[].class));
        }

        @Test
        @DisplayName("Should return the total amount for the 2 day booking in Cataratas 2")
        void shouldReturnTheTotalAmountForTheTwoDayBookingInCataratasTwo()
                        throws InvalidUsernameException, ParseException, InvalidDateException, InvalidCityException,
                        InvalidHotelException, InvalidRoomTypeException, NoAvailableRoomException,
                        JsonProcessingException {

                when(userRepository.findUserByUsername(bookingRequest.getUsername())).thenReturn(userStub);
                when(cityRepository.findCityByName(bookingRequest.getBooking().getDestination())).thenReturn(cityStub);

                when(hotelRepository.findHotelByCode(bookingRequest.getBooking().getHotelCode())).thenReturn(hotelStub);

                when(roomTypeRepository.findRoomTypeByName(bookingRequest.getBooking().getRoomType()))
                                .thenReturn(roomTypesStub);
                when(roomRepository.findAvailableRoomsByHotelAndRoomType(bookingRequest.getBooking().getHotelCode(),
                                bookingRequest.getBooking().getRoomType())).thenReturn(roomStub);

                BookingResponse result = this.bookingService.saveBooking(bookingRequest);

                assertEquals(objectMapper.writeValueAsString(bookingResponse), objectMapper.writeValueAsString(result));
        }
}
