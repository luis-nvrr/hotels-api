package meli.challenge.quality.domain.services;

import java.text.ParseException;

import meli.challenge.quality.application.dtos.BookingRequest;
import meli.challenge.quality.application.dtos.BookingResponse;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.exceptions.InvalidCityException;
import meli.challenge.quality.domain.exceptions.InvalidHotelException;
import meli.challenge.quality.domain.exceptions.InvalidRoomTypeException;
import meli.challenge.quality.domain.exceptions.InvalidUsernameException;
import meli.challenge.quality.domain.exceptions.NoAvailableRoomException;

public interface BookingService {
  BookingResponse saveBooking(BookingRequest bookingRequest)
      throws InvalidUsernameException, ParseException, InvalidDateException, InvalidCityException,
      InvalidHotelException, InvalidRoomTypeException, NoAvailableRoomException;
}
