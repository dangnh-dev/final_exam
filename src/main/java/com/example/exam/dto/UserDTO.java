package com.example.exam.dto;

import com.example.exam.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private List<RoleDTO> roles;
    private List<RoomBookDTO> roomBookDTOList;

    public UserDTO(UserEntity userEntity){
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.username = userEntity.getUsername();
        this.phone = userEntity.getPhone();
        if(userEntity.getRoles() != null){
            this.roles = userEntity.getRoles().stream().map(x -> new RoleDTO(x)).collect(Collectors.toList());
        }
        if (userEntity.getRoomBookEntityList() != null && !userEntity.getRoomBookEntityList().isEmpty()){
            this.roomBookDTOList = userEntity.getRoomBookEntityList().stream().map(rb -> new RoomBookDTO(rb)).collect(Collectors.toList());
        }
    }
}
