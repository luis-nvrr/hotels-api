package meli.challenge.quality.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hotel {
  private String code;
  private String name;
  private City city;

  public String getCityName() {
    return city.getName();
  }
}
