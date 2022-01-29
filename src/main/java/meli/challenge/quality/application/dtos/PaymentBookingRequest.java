package meli.challenge.quality.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentBookingRequest {
  private String type;
  private String number;
  private int dues;
}
