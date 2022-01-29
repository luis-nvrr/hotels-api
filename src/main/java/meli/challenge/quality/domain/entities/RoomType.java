package meli.challenge.quality.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.challenge.quality.domain.utils.StringNormalizer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {
  private String name;
  private int maxPeopleAmount;

  public boolean isForLessThanEqualPeople(int peopleAmount) {
    return peopleAmount < this.maxPeopleAmount;
  }

  public boolean isNamed(String queryName) {
    return StringNormalizer.compareNormalizedStrings(name, queryName);
  }
}
