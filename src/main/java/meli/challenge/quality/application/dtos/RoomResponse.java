package meli.challenge.quality.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {
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
}
