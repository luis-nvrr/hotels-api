package meli.challenge.quality.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionResponse {
  private int statusCode;
  private String message;
}
