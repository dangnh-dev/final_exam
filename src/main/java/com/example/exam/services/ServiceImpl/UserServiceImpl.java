package com.example.exam.services.ServiceImpl;

import com.example.exam.dto.UserDTO;
import com.example.exam.entities.RoleEntity;
import com.example.exam.entities.UserEntity;
import com.example.exam.repositories.RoleRepository;
import com.example.exam.repositories.UserRepository;
import com.example.exam.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDTO signup(UserDTO userDTO) {
        RoleEntity roleEntity = roleRepository.findByName("ROLE_USER");
        UserEntity existUser = userRepository.findByEmail(userDTO.getEmail()).orElse(null);
        if (existUser == null){
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setUsername(userDTO.getUsername());
            userEntity.setPhone(userDTO.getPhone());
            userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userEntity.setRoles(Set.of(roleEntity));
            return new UserDTO(userRepository.save(userEntity));
        }
        return null;
    }
}
