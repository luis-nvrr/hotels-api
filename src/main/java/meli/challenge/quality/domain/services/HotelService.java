package meli.challenge.quality.domain.services;

import java.util.List;

import meli.challenge.quality.application.dtos.HotelRoomResponse;

public interface HotelService {

  List<HotelRoomResponse> findAllHotels();
}
