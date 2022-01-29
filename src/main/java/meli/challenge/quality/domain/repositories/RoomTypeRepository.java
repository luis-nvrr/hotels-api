package meli.challenge.quality.domain.repositories;

import meli.challenge.quality.domain.entities.RoomType;

public interface RoomTypeRepository {
  RoomType findRoomTypeByName(String roomTypeName);
}
