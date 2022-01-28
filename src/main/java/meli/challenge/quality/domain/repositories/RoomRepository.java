package meli.challenge.quality.domain.repositories;

import java.util.Date;
import java.util.List;

import meli.challenge.quality.domain.entities.Room;

public interface RoomRepository {

  List<Room> findAvailableRoomsByStartDateAndEndDateAndCity(Date availableSince,
      Date availableUntil, String name);

  List<Room> findAllAvailableRooms();

  List<Room> findAvailableRoomsByStartDate(Date startDate);
}
