package meli.challenge.quality.infrastructure.adapters;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.Room;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.infrastructure.repositories.SqlRoomRepository;

@Repository
public class SqlRoomAdapter implements RoomRepository {
  private SqlRoomRepository sqlRoomRepository;

  @Override
  public List<Room> findByAvailableSinceLessThanEqualAndAvailableUntilGreaterThanEqualAndCity_Name(Date availableSince,
      Date availableUntil, String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Room> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

}
