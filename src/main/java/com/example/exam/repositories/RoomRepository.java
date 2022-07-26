package com.example.exam.repositories;

import com.example.exam.entities.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    @Query("select r from RoomEntity r where r.status = false ")
    Page<RoomEntity> getRoomEmpty(Pageable pageable);
}
