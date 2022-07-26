package com.example.exam.dto;

import com.example.exam.entities.RoomBookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookDTO {
    private Long id;
    private LocalDate fromDay;
    private LocalDate toDay;
    private RoomDTO roomDTO;
    private Long userId;
    private Long roomId;

    public RoomBookDTO(RoomBookEntity roomBookEntity){
        this.id = roomBookEntity.getId();
        this.fromDay = roomBookEntity.getFromDay();
        this.toDay = roomBookEntity.getToDay();
        if (roomBookEntity.getRoomEntity() != null) {
            this.roomDTO = new RoomDTO(roomBookEntity.getRoomEntity());
        }
    }
}
