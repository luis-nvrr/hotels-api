package meli.challenge.quality.domain.services;

import java.util.List;

import meli.challenge.quality.application.dtos.HotelRoomResponse;
import meli.challenge.quality.domain.exceptions.InvalidDateException;

public interface HotelService {

  List<HotelRoomResponse> findAllAvailableRooms();

  List<HotelRoomResponse> findAvailableRoomsByStartDateAndEndDateAndCity(String startDate, String endDate, String name)
      throws InvalidDateException;

  List<HotelRoomResponse> findAvailableRoomsByStartDate(String startDate) throws InvalidDateException;

  List<HotelRoomResponse> findAvailableRoomsByEndDate(String endDate) throws InvalidDateException;
}