package meli.challenge.quality.infrastructure.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import meli.challenge.quality.domain.entities.Flight;
import meli.challenge.quality.domain.repositories.FlightRepository;

public class FlightRepositoryImpl implements FlightRepository {
  private Set<Flight> repository;

  public FlightRepositoryImpl() {
    this.repository = new HashSet<>();
  }

  @Override
  public void saveFligth(Flight flight) {
    this.repository.add(flight);
  }

  @Override
  public List<Flight> findFligthByOriginAndDestinationAndGoingDateAndComingDate(String originName,
      String destinationName,
      Date goingDate, Date comingDate) {

    List<Flight> flights = repository.stream()
        .filter(flight -> (flight.hasOriginCity(originName)
            && flight.hasDestinationCity(destinationName)
            && flight.hasGoingDate(goingDate)
            && flight.hasComingDate(comingDate)))
        .collect(Collectors.toList());

    return flights;
  }

  @Override
  public List<Flight> findAllFlights() {
    List<Flight> allFlights = new ArrayList<>();
    allFlights.addAll(this.repository);
    return allFlights;
  }

}
