package meli.challenge.quality.domain.repositories;

import java.util.Date;
import java.util.List;

import meli.challenge.quality.domain.entities.Flight;

public interface FlightRepository {
  void saveFligth(Flight flight);

  List<Flight> findFligthByOriginAndDestinationAndGoingDateAndComingDate(String originName, String destinationName,
      Date goingDate, Date comingDate);

  List<Flight> findAllFlights();

}
