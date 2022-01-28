package meli.challenge.quality.domain.services;

import java.util.List;

import meli.challenge.quality.application.dtos.RoomResponse;
import meli.challenge.quality.domain.exceptions.InvalidDateException;

public interface RoomService {

        List<RoomResponse> findAllAvailableRooms();

        List<RoomResponse> findAvailableRoomsByStartDateAndEndDateAndCity(String startDate, String endDate,
                        String name)
                        throws InvalidDateException;

        List<RoomResponse> findAvailableRoomsByStartDate(String startDate) throws InvalidDateException;

        List<RoomResponse> findAvailableRoomsByEndDate(String endDate) throws InvalidDateException;

        List<RoomResponse> findAvailableRoomsByStartDateAndCity(String startDate, String cityName)
                        throws InvalidDateException;

        List<RoomResponse> findAvailableRoomsByEndDateAndCity(String endDate, String cityName)
                        throws InvalidDateException;

}