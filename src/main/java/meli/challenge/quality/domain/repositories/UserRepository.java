package meli.challenge.quality.domain.repositories;

import java.util.List;

import meli.challenge.quality.domain.entities.User;

public interface UserRepository {
  User findUserByUsername(String username);

  void saveUser(User user);

  List<User> findAllUsers();
}
