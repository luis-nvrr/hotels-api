package meli.challenge.quality.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import meli.challenge.quality.infrastructure.entities.SqlRoom;

public interface SqlRoomRepository extends JpaRepository<SqlRoom, String> {

}
