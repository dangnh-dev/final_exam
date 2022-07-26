package com.example.exam.dto;


import com.example.exam.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {
    private Integer id;
    private String name;
    public RoleDTO(RoleEntity roleEntity){
        this.id = roleEntity.getId();
        this.name = roleEntity.getName();
    }
}
