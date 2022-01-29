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
import java.util.Date;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import meli.challenge.quality.application.dtos.BookingRequest;
import meli.challenge.quality.application.dtos.BookingResponse;
import meli.challenge.quality.application.services.BookingServiceImpl;
import meli.challenge.quality.domain.entities.Booking;
import meli.challenge.quality.domain.entities.City;
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
import meli.challenge.quality.mocks.CityMock;
import meli.challenge.quality.mocks.HotelMock;
import meli.challenge.quality.mocks.RoomMock;
import meli.challenge.quality.mocks.RoomTypeMock;
import meli.challenge.quality.mocks.UserMock;

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

        private UserMock userMock;
        private CityMock cityMock;
        private HotelMock hotelMock;
        private RoomTypeMock roomTypeMock;
        private RoomMock roomMock;

        private final ObjectMapper mapper = new ObjectMapper();
        private BookingRequest bookingRequest;

        @BeforeEach
        public void setUp() throws StreamReadException, DatabindException, IOException, ParseException {
                openMocks(this);
                this.bookingService = new BookingServiceImpl(bookingRepository, userRepository, cityRepository,
                                hotelRepository,
                                roomTypeRepository, roomRepository);

                this.bookingRequest = mapper.readValue(new File("src/test/resources/hotels/bookingRequest.json"),
                                new TypeReference<>() {
                                });
                this.userMock = new UserMock();
                this.cityMock = new CityMock();
                this.hotelMock = new HotelMock(cityMock);
                this.roomTypeMock = new RoomTypeMock();
                this.roomMock = new RoomMock(hotelMock, roomTypeMock);
        }

        @Test
        @DisplayName("Should return the total amount for the 2 day booking in Cataratas 2")
        void shouldReturnTheTotalAmountForTheTwoDayBookingInCataratasTwo()
                        throws InvalidUsernameException, ParseException, InvalidDateException, InvalidCityException,
                        InvalidHotelException, InvalidRoomTypeException, NoAvailableRoomException {

                Booking emptyBooking = new Booking();

                when(userRepository.findUserByUsername(bookingRequest.getUsername()))
                                .thenReturn(userMock.findUserByUsername(bookingRequest.getUsername()));

                when(cityRepository.findCityByName(bookingRequest.getBooking().getDestination()))
                                .thenReturn(cityMock.get(bookingRequest.getBooking().getDestination()));

                when(hotelRepository.findHotelByCode(bookingRequest.getBooking().getHotelCode()))
                                .thenReturn(hotelMock.get(bookingRequest.getBooking().getHotelCode()));

                when(roomTypeRepository.findRoomTypeByName(bookingRequest.getBooking().getRoomType()))
                                .thenReturn(roomTypeMock.get(bookingRequest.getBooking().getRoomType()));

                when(roomRepository.findAvailableRoomsByHotelAndRoomType(bookingRequest.getBooking().getHotelCode(),
                                bookingRequest.getBooking().getRoomType())).thenReturn(roomMock.findBookingMock());

                when(bookingRepository.saveBooking(emptyBooking)).thenReturn(emptyBooking);

                assertEquals(16400, this.bookingService.saveBooking(bookingRequest).getTotalAmountForBooking());
        }
}
