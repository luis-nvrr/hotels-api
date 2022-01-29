package meli.challenge.quality.infrastructure.adapters;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.Hotel;
import meli.challenge.quality.domain.repositories.HotelRepository;

@Repository
public class SqlHotelAdapter implements HotelRepository {

  @Override
  public Hotel findHotelByCode(String hotelCode) {
    // TODO Auto-generated method stub
    return null;
  }

}
