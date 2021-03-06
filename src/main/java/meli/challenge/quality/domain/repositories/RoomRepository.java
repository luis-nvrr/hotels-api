package meli.challenge.quality.domain.repositories;

import java.util.Date;
import java.util.List;

import meli.challenge.quality.domain.entities.Room;

public interface RoomRepository {

  void saveRoom(Room room);

  List<Room> findAvailableRoomsByStartDateAndEndDateAndCity(Date availableSince,
      Date availableUntil, String name);

  List<Room> findAllAvailableRooms();

  List<Room> findAvailableRoomsByStartDate(Date startDate);

  List<Room> findAvailableRoomsByEndDate(Date endDate);

  List<Room> findAvailableRoomsByStartDateAndCity(Date startDate, String cityName);

  List<Room> findAvailableRoomsByEndDateAndCity(Date endDate, String cityName);

  List<Room> findAvailableRoomsByHotelAndRoomType(String hotelCode, String roomType);

  List<Room> findAllRooms();
}
