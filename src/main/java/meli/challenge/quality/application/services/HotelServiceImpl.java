package meli.challenge.quality.application.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import meli.challenge.quality.application.dtos.HotelRoomResponse;
import meli.challenge.quality.domain.entities.Room;
import meli.challenge.quality.domain.repositories.RoomRepository;
import meli.challenge.quality.domain.services.HotelService;

@Service
@NoArgsConstructor
public class HotelServiceImpl implements HotelService {

  private RoomRepository roomRepository;
  private final Logger logger = Logger.getLogger(HotelServiceImpl.class);

  public HotelServiceImpl(RoomRepository hotelRoomRepository) {
    this.roomRepository = hotelRoomRepository;
  }

  @Override
  public List<HotelRoomResponse> findAllHotels() {
    List<Room> rooms = roomRepository.findAll();
    List<HotelRoomResponse> roomsResponse = new ArrayList<>();

    for (Room room : rooms) {
      IHotelRoomResponseBuilder builder = new HotelRoomResponseBuilder(room);
      builder.build();
      roomsResponse.add(builder.getResponse());
    }

    logger.error(roomsResponse);

    return roomsResponse;
  }

  public interface IHotelRoomResponseBuilder {
    void build();

    HotelRoomResponse getResponse();
  }

  public class HotelRoomResponseBuilder implements IHotelRoomResponseBuilder {
    private HotelRoomResponse response;
    private Room room;
    private final String DATE_PATTERN = "dd/MM/yyyy";
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

    public HotelRoomResponseBuilder(Room room) {
      this.response = new HotelRoomResponse();
      this.room = room;
    }

    public void build() {
      response.setCode(room.getCode());
      response.setName(room.getHotelName());
      response.setCity(room.getCityName());
      response.setRoomType(room.getRoomTypeName());
      response.setPriceByNight(String.format("$%d", room.getPriceByNight()));
      response.setAvailableSinceDate(dateFormat.format(room.getAvailableSince()));
      response.setAvailableUntilDate(dateFormat.format(room.getAvailableUntil()));
      response.setIsBooked(room.isBooked() ? "SI" : "NO");
    }

    public HotelRoomResponse getResponse() {
      return this.response;
    }
  }
}
