package meli.challenge.quality.domain.services;

import java.text.ParseException;
import java.util.List;

import meli.challenge.quality.application.dtos.HotelRoomResponse;
import meli.challenge.quality.domain.exceptions.InvalidDateException;

public interface HotelService {

  List<HotelRoomResponse> findAllHotels();

  List<HotelRoomResponse> findByAvailableFromDateToDateAndByCity(String startDate, String endDate, String name)
      throws InvalidDateException;
}