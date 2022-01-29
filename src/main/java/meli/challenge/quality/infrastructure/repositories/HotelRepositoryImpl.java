package meli.challenge.quality.infrastructure.repositories;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.Hotel;
import meli.challenge.quality.domain.repositories.HotelRepository;

@Repository
public class HotelRepositoryImpl implements HotelRepository {
  private Map<String, Hotel> repository;

  public HotelRepositoryImpl() {
    this.repository = new Hashtable<>();
  }

  @Override
  public Hotel findHotelByCode(String hotelCode) {
    return this.repository.get(hotelCode);
  }

  @Override
  public void saveHotel(Hotel hotel) {
    this.repository.putIfAbsent(hotel.getCode(), hotel);
  }

  @Override
  public List<Hotel> findAllHotels() {
    List<Hotel> hotels = new ArrayList<>();
    hotels.addAll(repository.values());
    return hotels;
  }

}
