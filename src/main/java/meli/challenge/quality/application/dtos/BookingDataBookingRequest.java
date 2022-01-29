package meli.challenge.quality.application.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDataBookingRequest {
  private String dateFrom;
  private String dateTo;
  private String destination;
  private String hotelCode;
  private int peopleAmount;
  private String roomType;
  private List<PeopleBookingRequest> people;
  private PaymentBookingRequest paymentMethod;

}