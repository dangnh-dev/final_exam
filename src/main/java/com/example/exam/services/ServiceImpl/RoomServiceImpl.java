package com.example.exam.services.ServiceImpl;

import com.example.exam.dto.RoomDTO;
import com.example.exam.entities.RoomEntity;
import com.example.exam.repositories.RoomRepository;
import com.example.exam.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class RoomServiceImpl implements IRoomService {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public List<RoomDTO> getAllRoom() {
        return roomRepository.findAll().stream().map(r -> new RoomDTO(r)).collect(Collectors.toList());
    }

    @Override
    public Page<RoomDTO> getRoomEmpty(Pageable pageable) {
        return roomRepository.getRoomEmpty(pageable).map(r -> new RoomDTO(r));
    }

    @Override
    public RoomDTO findById(Long id) {
        return new RoomDTO(roomRepository.findById(id).get());
    }

    @Override
    public RoomDTO createOrUpdate(RoomDTO roomDTO) {
        RoomEntity roomEntity = null;
        if (roomDTO.getId() != null) {
            roomEntity = roomRepository.findById(roomDTO.getId()).orElse(null);
            roomEntity.setStatus(roomDTO.getStatus());
        }
        if (roomEntity == null) {
            roomEntity = new RoomEntity();
            roomEntity.setStatus(false);
        }
        roomEntity.setName(roomDTO.getName());
        roomEntity.setPrice(roomDTO.getPrice());
        roomEntity.setImage(roomDTO.getImage());
        return new RoomDTO(roomRepository.save(roomEntity));
    }

    @Override
    public boolean delete(Long id) {
        try {
            roomRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
