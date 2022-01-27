package meli.challenge.quality.mocks;

import java.util.Hashtable;
import java.util.Map;

import meli.challenge.quality.entities.RoomType;

public class RoomTypeMock {
  private final Map<String, RoomType> mock;

  public RoomTypeMock() {
    this.mock = new Hashtable<>();
    this.load();
  }

  public void load() {
    RoomType single = new RoomType("Single");
    RoomType doble = new RoomType("Doble");
    RoomType triple = new RoomType("Triple");
    RoomType multiple = new RoomType("Múltiple");
    RoomType types[] = { single, doble, triple, multiple };
    for (RoomType type : types) {
      mock.put(type.getName(), type);
    }
  }

  public RoomType get(String name) {
    return this.mock.get(name);
  }
}
