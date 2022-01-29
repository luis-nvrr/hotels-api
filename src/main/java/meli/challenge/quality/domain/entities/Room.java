package meli.challenge.quality.domain.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.challenge.quality.domain.utils.DateComparer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
  private Hotel hotel;
  private RoomType roomType;
  private int priceByNight;
  private Date availableSince;
  private Date availableUntil;
  private boolean booked;

  public String getHotelName() {
    return this.hotel.getName();
  }

  public String getHotelCode() {
    return this.hotel.getCode();
  }

  public String getCityName() {
    return this.hotel.getCityName();
  }

  public String getRoomTypeName() {
    return this.roomType.getName();
  }

  public boolean isForLessThanEqualPeople(int peopleAmount) {
    return roomType.isForLessThanEqualPeople(peopleAmount);
  }

  public boolean isAvailableFromDate(Date queryDate) {
    return DateComparer.compareGreatThanOrEqual(queryDate, availableSince);
  }

  public boolean isAvailableUntilDate(Date queryDate) {
    return DateComparer.compareLessThanOrEqual(queryDate, availableUntil);
  }

  public boolean isInCityNamed(String cityName) {
    return this.hotel.isInCityNamed(cityName);
  }

  public boolean isAvailable() {
    return !booked;
  }

  public boolean isInHotelWithCode(String hotelCode) {
    return this.hotel.hasCode(hotelCode);
  }

  public boolean hasRoomTypeNamed(String roomTypeName) {
    return this.roomType.isNamed(roomTypeName);
  }
}
