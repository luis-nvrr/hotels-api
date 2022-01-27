package meli.challenge.quality.repositories;

import java.util.Date;
import java.util.List;

import meli.challenge.quality.entities.Room;

public interface RoomRepository {

  List<Room> findByAvailableSinceLessThanEqualAndAvailableUntilGreaterThanEqualAndCity_Name(Date availableSince,
      Date availableUntil, String name);

  List<Room> findAll();
}
