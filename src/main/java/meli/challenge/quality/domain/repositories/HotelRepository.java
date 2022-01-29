package meli.challenge.quality.domain.repositories;

import java.util.List;

import meli.challenge.quality.domain.entities.Hotel;

public interface HotelRepository {
  Hotel findHotelByCode(String hotelCode);

  void saveHotel(Hotel hotel);

  List<Hotel> findAllHotels();
}
