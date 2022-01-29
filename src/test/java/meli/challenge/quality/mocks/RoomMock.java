package meli.challenge.quality.mocks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import meli.challenge.quality.domain.entities.Room;

public class RoomMock {

        private final String DATE_FORMAT = "dd/MM/yyyy";
        private final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        private final Map<Integer, Room> mock;
        private final HotelMock hotelMock;
        private final RoomTypeMock typeMock;

        public RoomMock(HotelMock hotelMock, RoomTypeMock typeMock) throws ParseException {
                this.hotelMock = hotelMock;
                this.typeMock = typeMock;
                this.mock = new Hashtable<>();
                load();
        }

        public void load() throws ParseException {
                Room room1 = new Room(1, hotelMock.get("CH-0002"), typeMock.get("Doble"), 6300,
                                formatter.parse("10/02/2021"),
                                formatter.parse("20/03/2021"), false);

                Room room2 = new Room(2, hotelMock.get(
                                "CH-0003"),
                                typeMock.get(
                                                "Triple"),
                                8200, formatter.parse("10/02/2021"),
                                formatter.parse("23/03/2021"), false);

                Room room3 = new Room(3, hotelMock.get(
                                "HB-0001"),
                                typeMock.get(
                                                "Single"),
                                5435, formatter.parse("10/02/2021"),
                                formatter.parse("19/03/2021"), false);

                Room room4 = new Room(4, hotelMock.get(
                                "BH-0002"),
                                typeMock.get(
                                                "Doble"),
                                6300, formatter.parse("10/02/2021"),
                                formatter.parse("20/03/2021"), false);

                Room room5 = new Room(5, hotelMock.get(
                                "SH-0002"),
                                typeMock.get(
                                                "Doble"),
                                5790, formatter.parse("17/04/2021"),
                                formatter.parse("23/05/2021"), false);

                Room room6 = new Room(6, hotelMock.get(
                                "SH-0001"),
                                typeMock.get(
                                                "Single"),
                                4150, formatter.parse("02/01/2021"),
                                formatter.parse("19/02/2021"), false);

                Room room7 = new Room(7, hotelMock.get(
                                "SE-0001"),
                                typeMock.get(
                                                "Single"),
                                3900, formatter.parse("23/01/2021"),
                                formatter.parse("23/11/2021"), false);

                Room room8 = new Room(8, hotelMock.get(
                                "SE-0002"),
                                typeMock.get(
                                                "Doble"),
                                5840, formatter.parse("23/01/2021"),
                                formatter.parse("15/10/2021"), false);

                Room room9 = new Room(9, hotelMock.get(
                                "EC-0003"),
                                typeMock.get(
                                                "Triple"),
                                7020, formatter.parse("15/02/2021"),
                                formatter.parse("27/03/2021"), false);

                Room room10 = new Room(10, hotelMock.get(
                                "CP-0004"),
                                typeMock.get(
                                                "Múltiple"),
                                8600, formatter.parse("01/03/2021"),
                                formatter.parse("17/04/2021"), false);

                Room room11 = new Room(11, hotelMock.get(
                                "CP-0002"),
                                typeMock.get(
                                                "Doble"),
                                6400, formatter.parse("10/02/2021"),
                                formatter.parse("20/03/2021"), false);

                Room room12 = new Room(12, hotelMock.get(
                                "BG-0004"),
                                typeMock.get(
                                                "Múltiple"),
                                9370, formatter.parse("17/04/2021"),
                                formatter.parse("12/06/2021"), false);

                Room rooms[] = { room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, room11,
                                room12 };

                for (Room room : rooms) {
                        this.mock.put(room.getNumber(), room);
                }
        }

        public List<Room> findAll() {
                return new ArrayList<>(mock.values());
        }

        public Room get(int code) {
                return this.mock.get(code);
        }

        public List<Room> findRoomsInBuenosAiresFromFirstFebruaryToFirstMay() {
                List<Room> rooms = new ArrayList<>();
                rooms.add(mock.get(7));
                rooms.add(mock.get(4));
                return rooms;
        }

        public List<Room> findAvailableRoomsFromFirstApril() {
                List<Room> rooms = new ArrayList<>();
                rooms.add(mock.get(5));
                rooms.add(mock.get(12));
                return rooms;
        }

        public List<Room> findAvailableRoomsUntilFirstMarch() {
                List<Room> rooms = new ArrayList<>();
                rooms.add(mock.get(7));
                return rooms;
        }

        public List<Room> findAvailableRoomsFromFirstAprilInTucuman() {
                List<Room> rooms = new ArrayList<>();
                rooms.add(mock.get(5));
                return rooms;
        }

        public List<Room> findAvailableRoomsUntilFirstMarchInTucuman() {
                List<Room> rooms = new ArrayList<>();
                rooms.add(mock.get(7));
                return rooms;
        }

        public List<Room> findBookingMock() {
                List<Room> rooms = new ArrayList<>();
                rooms.add(mock.get(2));
                return rooms;
        }
}
