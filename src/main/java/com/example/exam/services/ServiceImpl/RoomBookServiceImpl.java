package com.example.exam.services.ServiceImpl;

import com.example.exam.dto.RoomBookDTO;
import com.example.exam.entities.RoomBookEntity;
import com.example.exam.entities.UserEntity;
import com.example.exam.repositories.RoomBookRepository;
import com.example.exam.repositories.RoomRepository;
import com.example.exam.repositories.UserRepository;
import com.example.exam.services.IRoomBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomBookServiceImpl implements IRoomBookService {
    @Autowired
    RoomBookRepository roomBookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;

    @Override
    public RoomBookDTO createOrUpdate(RoomBookDTO roomBookDTO) {
        RoomBookEntity roomBookEntity = null;
        if (roomBookDTO.getId() != null)
            roomBookEntity = roomBookRepository.findById(roomBookDTO.getId()).orElse(null);
        if (roomBookEntity == null)
            roomBookEntity = new RoomBookEntity();
        roomBookEntity.setUserEntity(userRepository.findById(roomBookDTO.getUserId()).get());
        roomBookEntity.setRoomEntity(roomRepository.findById(roomBookDTO.getRoomId()).get());
        roomBookEntity.setFromDay(roomBookDTO.getFromDay());
        roomBookEntity.setToDay(roomBookDTO.getToDay());
        return new RoomBookDTO(roomBookRepository.save(roomBookEntity));
    }

    @Override
    public RoomBookDTO findById(Long id) {
        return new RoomBookDTO(roomBookRepository.findById(id).get());
    }
}
