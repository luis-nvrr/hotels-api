package meli.challenge.quality.mocks;

import java.util.Hashtable;
import java.util.Map;

import meli.challenge.quality.domain.entities.User;

public class UserMock {
  private Map<String, User> mock;

  public UserMock() {
    this.mock = new Hashtable<>();
    load();
  }

  private void load() {
    User user = new User("seba_gonzalez@unmail.com.ar");
    this.mock.put(user.getUsername(), user);
  }

  public User findUserByUsername(String username) {
    return this.mock.get(username);
  }
}
