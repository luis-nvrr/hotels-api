package meli.challenge.quality.infrastructure.adapters;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.RoomType;
import meli.challenge.quality.domain.repositories.RoomTypeRepository;

@Repository
public class SqlRoomTypeAdapter implements RoomTypeRepository {

  @Override
  public RoomType findRoomTypeByName(String roomTypeName) {
    // TODO Auto-generated method stub
    return null;
  }

}
