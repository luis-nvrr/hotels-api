package meli.challenge.quality.mocks;

import java.util.Hashtable;
import java.util.Map;

import meli.challenge.quality.domain.entities.RoomType;

public class RoomTypeMock {
  private final Map<String, RoomType> mock;

  public RoomTypeMock() {
    this.mock = new Hashtable<>();
    this.load();
  }

  public void load() {
    RoomType single = new RoomType("single", 1);
    RoomType doble = new RoomType("doble", 2);
    RoomType triple = new RoomType("triple", 3);
    RoomType multiple = new RoomType("múltiple", 10);
    RoomType types[] = { single, doble, triple, multiple };
    for (RoomType type : types) {
      mock.put(type.getName(), type);
    }
  }

  public RoomType get(String name) {
    return this.mock.get(name.toLowerCase());
  }
}
