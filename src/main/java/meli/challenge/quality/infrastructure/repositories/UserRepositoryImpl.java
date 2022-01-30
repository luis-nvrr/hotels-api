package meli.challenge.quality.infrastructure.repositories;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.User;
import meli.challenge.quality.domain.repositories.UserRepository;
import meli.challenge.quality.domain.utils.StringNormalizer;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private Map<String, User> repository;

  public UserRepositoryImpl() {
    this.repository = new Hashtable<>();
  }

  @Override
  public User findUserByUsername(String username) {
    String normalizedUsername = StringNormalizer.normalizeStringToKey(username);
    return this.repository.get(normalizedUsername);
  }

  @Override
  public void saveUser(User user) {
    String normalizedUsername = StringNormalizer.normalizeStringToKey(user.getUsername());
    this.repository.putIfAbsent(normalizedUsername, user);
  }

  @Override
  public List<User> findAllUsers() {
    List<User> users = new ArrayList<>();
    users.addAll(repository.values());
    return users;
  }
}
