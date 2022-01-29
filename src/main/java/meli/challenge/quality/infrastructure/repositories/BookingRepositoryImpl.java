package meli.challenge.quality.infrastructure.repositories;

import java.util.Set;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.Booking;
import meli.challenge.quality.domain.repositories.BookingRepository;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

  private Set<Booking> repository;

  @Override
  public Booking saveBooking(Booking booking) {
    this.repository.add(booking);
    return booking;
  }

}
