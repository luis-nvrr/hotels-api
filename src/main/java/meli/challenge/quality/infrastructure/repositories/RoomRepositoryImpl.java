package meli.challenge.quality.infrastructure.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import meli.challenge.quality.domain.entities.Room;
import meli.challenge.quality.domain.repositories.RoomRepository;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

  private Set<Room> repository;

  public RoomRepositoryImpl() {
    this.repository = new HashSet<>();
  }

  @Override
  public List<Room> findAllRooms() {
    List<Room> rooms = new ArrayList<>();
    rooms.addAll(repository);
    return rooms;
  }

  @Override
  public List<Room> findAvailableRoomsByStartDateAndEndDateAndCity(Date availableSince, Date availableUntil,
      String cityName) {
    List<Room> matchingRooms = this.repository
        .stream()
        .filter(
            room -> (room.isAvailableFromDate(availableSince)
                && room.isAvailableUntilDate(availableUntil)
                && room.isInCityNamed(cityName)))
        .collect(Collectors.toList());

    return matchingRooms;
  }

  @Override
  public List<Room> findAllAvailableRooms() {
    List<Room> matchingRooms = this.repository
        .stream()
        .filter(Room::isAvailable)
        .collect(Collectors.toList());

    return matchingRooms;
  }

  @Override
  public List<Room> findAvailableRoomsByStartDate(Date startDate) {
    List<Room> matchingRooms = this.repository
        .stream()
        .filter(room -> room.isAvailableFromDate(startDate))
        .collect(Collectors.toList());

    return matchingRooms;
  }

  @Override
  public List<Room> findAvailableRoomsByEndDate(Date endDate) {
    List<Room> matchingRooms = this.repository
        .stream()
        .filter(room -> room.isAvailableUntilDate(endDate))
        .collect(Collectors.toList());

    return matchingRooms;
  }

  @Override
  public List<Room> findAvailableRoomsByStartDateAndCity(Date startDate, String cityName) {
    List<Room> matchingRooms = this.repository
        .stream()
        .filter(room -> room.isAvailableFromDate(startDate)
            && room.isInCityNamed(cityName))
        .collect(Collectors.toList());

    return matchingRooms;
  }

  @Override
  public List<Room> findAvailableRoomsByEndDateAndCity(Date endDate, String cityName) {
    List<Room> matchingRooms = this.repository
        .stream()
        .filter(room -> room.isAvailableUntilDate(endDate)
            && room.isInCityNamed(cityName))
        .collect(Collectors.toList());

    return matchingRooms;
  }

  @Override
  public List<Room> findAvailableRoomsByHotelAndRoomType(String hotelCode, String roomType) {
    List<Room> matchingRooms = this.repository
        .stream()
        .filter(room -> room.isInHotelWithCode(hotelCode)
            && room.hasRoomTypeNamed(roomType))
        .collect(Collectors.toList());

    return matchingRooms;
  }

  @Override
  public void saveRoom(Room room) {
    this.repository.add(room);
  }

}
