package meli.challenge.quality.domain.utils;

import java.util.Hashtable;
import java.util.Map;

public class RoomTypeMapper {
  private static final Map<String, Integer> roomTypes;

  static {
    roomTypes = new Hashtable<>();
    roomTypes.put("single", 1);
    roomTypes.put("doble", 1);
    roomTypes.put("triple", 1);
    roomTypes.put("múltiple", 1);
  }

  public static final int getMaxPeopleAmount(String roomType) {
    return roomTypes.get(roomType.toLowerCase().trim());
  }
}
