package meli.challenge.quality.domain.repositories;

import java.util.Date;
import java.util.List;

import meli.challenge.quality.domain.entities.Room;

public interface RoomRepository {

  List<Room> findByAvailableFromDateToDateAndByCity(Date availableSince,
      Date availableUntil, String name);

  List<Room> findAll();
}
