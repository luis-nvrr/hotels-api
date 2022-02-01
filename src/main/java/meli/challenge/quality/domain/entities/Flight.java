package meli.challenge.quality.domain.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.challenge.quality.domain.utils.DateComparer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
  private String number;
  private City origin;
  private City destination;
  private SeatType seatType;
  private int priceByPerson;
  private Date goingDate;
  private Date comingDate;

  public boolean hasOriginCity(String cityName) {
    return origin.isNamed(cityName);
  }

  public boolean hasDestinationCity(String cityName) {
    return this.destination.isNamed(cityName);
  }

  public boolean hasGoingDate(Date goingDate) {
    return DateComparer.compareEquals(this.goingDate, goingDate);
  }

  public boolean hasComingDate(Date comingDate) {
    return DateComparer.compareEquals(this.comingDate, comingDate);
  }
}
