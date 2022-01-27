package meli.challenge.quality.services;

import java.util.List;

import meli.challenge.quality.dtos.HotelRoomResponse;

public interface HotelService {

  List<HotelRoomResponse> findAllHotels();
}
