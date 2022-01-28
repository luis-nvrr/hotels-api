package meli.challenge.quality.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoomResponse {
  private String code;
  private String name;
  private String city;
  private String roomType;
  private String priceByNight;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private String availableSinceDate;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private String availableUntilDate;
  private String isBooked;

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;

    if (!(o instanceof HotelRoomResponse))
      return false;

    HotelRoomResponse r = (HotelRoomResponse) o;
    return this.code == r.code && this.name == r.name && this.city == r.city && this.roomType == r.roomType
        && this.priceByNight == r.priceByNight && this.availableSinceDate == r.availableSinceDate
        && this.availableUntilDate == r.availableUntilDate && this.isBooked == r.isBooked;
  }
}
