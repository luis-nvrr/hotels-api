package meli.challenge.quality.domain.repositories;

import java.util.List;

import meli.challenge.quality.domain.entities.SeatType;

public interface SeatTypeRepository {
  SeatType findSeatTypeByName(String seatTypeName);

  void saveSeatType(SeatType seatType);

  List<SeatType> findAllSeatTypes();
}
