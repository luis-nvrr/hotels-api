package meli.challenge.quality.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleBookingRequest {
  private String dni;
  private String name;
  private String lastname;
  private String birthDate;
  private String mail;
}