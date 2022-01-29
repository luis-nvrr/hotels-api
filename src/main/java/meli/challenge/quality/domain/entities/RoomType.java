package meli.challenge.quality.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {
  private String name;
  private int maxPeopleAmount;

  public boolean isForLessThanEqualPeople(int peopleAmount) {
    return peopleAmount < this.maxPeopleAmount;
  }
}
