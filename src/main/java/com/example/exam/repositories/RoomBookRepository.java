package com.example.exam.repositories;

import com.example.exam.entities.RoomBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomBookRepository extends JpaRepository<RoomBookEntity, Long> {

}
