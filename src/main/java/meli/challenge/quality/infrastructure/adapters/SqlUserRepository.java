package meli.challenge.quality.infrastructure.adapters;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.User;
import meli.challenge.quality.domain.repositories.UserRepository;

@Repository
public class SqlUserRepository implements UserRepository {

  @Override
  public User findUserByUsername(String username) {
    // TODO Auto-generated method stub
    return null;
  }

}
