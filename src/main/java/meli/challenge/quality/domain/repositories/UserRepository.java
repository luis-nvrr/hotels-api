package meli.challenge.quality.domain.repositories;

import meli.challenge.quality.domain.entities.User;

public interface UserRepository {
  User findUserByUsername(String username);
}
