package meli.challenge.quality.infrastructure.repositories;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import meli.challenge.quality.domain.entities.SeatType;
import meli.challenge.quality.domain.repositories.SeatTypeRepository;
import meli.challenge.quality.domain.utils.StringNormalizer;

public class SeatTypeRepositoryImpl implements SeatTypeRepository {

  private Map<String, SeatType> repository;

  public SeatTypeRepositoryImpl() {
    this.repository = new Hashtable<>();
  }

  @Override
  public SeatType findSeatTypeByName(String seatTypeName) {
    String normalizedSeatType = StringNormalizer.normalizeStringToKey(seatTypeName);
    return this.repository.get(normalizedSeatType);
  }

  @Override
  public void saveSeatType(SeatType seatType) {
    String normalizedSeatType = StringNormalizer.normalizeStringToKey(seatType.getName());
    this.repository.put(normalizedSeatType, seatType);
  }

  @Override
  public List<SeatType> findAllSeatTypes() {
    List<SeatType> result = new ArrayList<>();
    result.addAll(this.repository.values());
    return result;
  }

}
