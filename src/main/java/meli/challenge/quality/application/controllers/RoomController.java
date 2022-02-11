package meli.challenge.quality.application.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import meli.challenge.quality.application.dtos.RoomRequest;
import meli.challenge.quality.application.dtos.RoomResponse;
import meli.challenge.quality.domain.exceptions.InvalidDateException;
import meli.challenge.quality.domain.services.RoomService;

@Controller
@RequestMapping("/rooms")
public class RoomController {
  private RoomService roomService;

  public RoomController(RoomService roomService) {
    this.roomService = roomService;
  }

  @GetMapping
  public ResponseEntity<List<RoomResponse>> findAllAvailableRooms() {
    return ResponseEntity.ok(roomService.findAllAvailableRooms());
  }

  @GetMapping(params = { "startDate", "endDate", "city" })
  public ResponseEntity<List<RoomResponse>> findAvailableRoomsByStartDateAndEndDateAndCity(
      @Valid RoomRequest roomRequest)
      throws InvalidDateException {
    return ResponseEntity.ok(roomService.findAvailableRoomsByStartDateAndEndDateAndCity(
        roomRequest.getStartDate(),
        roomRequest.getEndDate(), roomRequest.getCity()));
  }

  @GetMapping(params = { "startDate" })
  public ResponseEntity<List<RoomResponse>> findAvailableRoomsByStartDate(
      @RequestParam(name = "startDate", required = true) String startDate)
      throws InvalidDateException {
    return ResponseEntity.ok(roomService.findAvailableRoomsByStartDate(startDate));
  }

  @GetMapping(params = { "endDate" })
  public ResponseEntity<List<RoomResponse>> findAvailableRoomsByEndDate(
      @RequestParam(name = "endDate", required = true) String endDate)
      throws InvalidDateException {
    return ResponseEntity.ok(roomService.findAvailableRoomsByEndDate(endDate));
  }

  @GetMapping(params = { "startDate, city" })
  public ResponseEntity<List<RoomResponse>> findAvailableRoomsByStartDateAndCity(
      @RequestParam(name = "startDate", required = true) String startDate,
      @RequestParam(name = "city", required = true) String city)
      throws InvalidDateException {
    return ResponseEntity.ok(roomService.findAvailableRoomsByStartDateAndCity(startDate, city));
  }

  @GetMapping(params = { "endDate, city" })
  public ResponseEntity<List<RoomResponse>> findAvailableRoomsByEndDateAndCity(
      @RequestParam(name = "endDate", required = true) String endDate,
      @RequestParam(name = "city", required = true) String city)
      throws InvalidDateException {
    return ResponseEntity.ok(roomService.findAvailableRoomsByEndDateAndCity(endDate, city));
  }

}
