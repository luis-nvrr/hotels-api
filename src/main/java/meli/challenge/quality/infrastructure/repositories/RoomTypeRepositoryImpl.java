package meli.challenge.quality.infrastructure.repositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.RoomType;
import meli.challenge.quality.domain.repositories.RoomTypeRepository;

@Repository
public class RoomTypeRepositoryImpl implements RoomTypeRepository {

  private Set<RoomType> repository;

  public RoomTypeRepositoryImpl() {
    this.repository = new HashSet<>();
  }

  @Override
  public RoomType findRoomTypeByName(String roomTypeName) {
    RoomType result = this.repository.stream()
        .filter(roomType -> roomType.isNamed(roomTypeName))
        .findAny()
        .orElse(null);

    return result;
  }

  @Override
  public void saveRoomType(RoomType roomType) {
    this.repository.add(roomType);
  }

  @Override
  public List<RoomType> findAllRoomTypes() {
    List<RoomType> roomTypes = new ArrayList<>();
    roomTypes.addAll(this.repository);
    return roomTypes;
  }

}
