package meli.challenge.quality.domain.repositories;

import java.util.List;

import meli.challenge.quality.domain.entities.RoomType;

public interface RoomTypeRepository {
  RoomType findRoomTypeByName(String roomTypeName);

  void saveRoomType(RoomType roomType);

  List<RoomType> findAllRoomTypes();
}
