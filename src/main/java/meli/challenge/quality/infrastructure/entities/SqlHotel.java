package meli.challenge.quality.infrastructure.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SqlHotel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @Column
  private String name;
  @Column
  private SqlCity city;

  public String getCityName() {
    return city.getName();
  }
}
