package meli.challenge.quality.infrastructure.adapters;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.Booking;
import meli.challenge.quality.domain.repositories.BookingRepository;

@Repository
public class SqlBookingAdapter implements BookingRepository {

  @Override
  public Booking saveBooking(Booking booking) {
    // TODO Auto-generated method stub
    return null;
  }

}
