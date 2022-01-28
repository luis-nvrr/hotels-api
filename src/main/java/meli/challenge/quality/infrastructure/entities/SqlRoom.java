package meli.challenge.quality.infrastructure.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SqlRoom {
  @Id
  private String code;
  @Column
  private SqlHotel hotel;
  @Column
  private SqlRoomType roomType;
  @Column
  private int priceByNight;
  @Column
  private Date availableSince;
  @Column
  private Date availableUntil;
  @Column
  private boolean isBooked;

  public String getHotelName() {
    return this.hotel.getName();
  }

  public String getCityName() {
    return this.hotel.getCityName();
  }

  public String getRoomTypeName() {
    return this.roomType.getName();
  }

}
