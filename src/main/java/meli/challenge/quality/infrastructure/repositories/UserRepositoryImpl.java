package meli.challenge.quality.infrastructure.repositories;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.User;
import meli.challenge.quality.domain.repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private Map<String, User> repository;

  public UserRepositoryImpl() {
    this.repository = new Hashtable<>();
  }

  @Override
  public User findUserByUsername(String username) {
    return this.repository.get(username);
  }

  @Override
  public void saveUser(User user) {
    this.repository.putIfAbsent(user.getUsername(), user);
  }

  @Override
  public List<User> findAllUsers() {
    List<User> users = new ArrayList<>();
    users.addAll(repository.values());
    return users;
  }
}
