package meli.challenge.quality.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.challenge.quality.domain.utils.StringNormalizer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
  private String name;

  public boolean isNamed(String cityName) {
    return StringNormalizer.compareNormalizedStrings(name, cityName);
  }
}
