package meli.challenge.quality.domain.entities;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
  private User user;
  private Date dateFrom;
  private Date dateTo;
  private City destination;
  private Hotel hotel;
  private int peopleAmount;
  private Room room;

  public int calculateTotalAmount() {
    int numberOfDays = (int) TimeUnit.DAYS.convert(Math.abs(this.dateTo.getTime() - this.dateFrom.getTime()),
        TimeUnit.MILLISECONDS);
    return numberOfDays * room.getPriceByNight();
  }
}
