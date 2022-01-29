package meli.challenge.quality.domain.repositories;

import meli.challenge.quality.domain.entities.Booking;

public interface BookingRepository {
  Booking saveBooking(Booking booking);
}
