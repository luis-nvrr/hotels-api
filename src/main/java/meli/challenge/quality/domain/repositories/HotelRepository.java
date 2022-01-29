package meli.challenge.quality.domain.repositories;

import meli.challenge.quality.domain.entities.Hotel;

public interface HotelRepository {
  Hotel findHotelByCode(String hotelCode);
}
