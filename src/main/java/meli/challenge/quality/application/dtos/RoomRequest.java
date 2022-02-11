package meli.challenge.quality.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomRequest {
  @NotBlank
  private String startDate;
  @NotBlank
  private String endDate;
  @NotBlank
  private String city;
}
