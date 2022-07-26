package com.example.exam.dto;

import com.example.exam.entities.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Long id;
    private String name;
    private Double price;
    private String image;
    private Boolean status;

    public RoomDTO(RoomEntity roomEntity){
        this.id = roomEntity.getId();
        this.name = roomEntity.getName();
        this.price = roomEntity.getPrice();
        this.image = roomEntity.getImage();
        this.status = roomEntity.getStatus();
    }
}
