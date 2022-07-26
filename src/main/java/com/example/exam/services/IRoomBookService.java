package com.example.exam.services;

import com.example.exam.dto.RoomBookDTO;

public interface IRoomBookService {
    RoomBookDTO createOrUpdate(RoomBookDTO roomBookDTO);
    RoomBookDTO findById(Long id);
}
