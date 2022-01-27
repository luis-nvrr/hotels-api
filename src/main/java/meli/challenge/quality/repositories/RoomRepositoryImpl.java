package meli.challenge.quality.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.entities.Room;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

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
