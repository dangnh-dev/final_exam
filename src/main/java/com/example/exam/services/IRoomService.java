package com.example.exam.services;

import com.example.exam.dto.RoomDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoomService {
    List<RoomDTO> getAllRoom();
    Page<RoomDTO> getRoomEmpty(Pageable pageable);
    RoomDTO findById(Long id);
    RoomDTO createOrUpdate(RoomDTO roomDTO);
    boolean delete(Long id);
}
