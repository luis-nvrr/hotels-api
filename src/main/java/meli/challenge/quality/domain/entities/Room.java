package meli.challenge.quality.domain.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
  private int number;
  private Hotel hotel;
  private RoomType roomType;
  private int priceByNight;
  private Date availableSince;
  private Date availableUntil;
  private boolean isBooked;

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
}
