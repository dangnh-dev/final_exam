package com.example.exam.controllers;

import com.example.exam.dto.LoginRequest;
import com.example.exam.dto.RoomBookDTO;
import com.example.exam.dto.RoomDTO;
import com.example.exam.dto.UserDTO;
import com.example.exam.entities.RoleEntity;
import com.example.exam.entities.UserEntity;
import com.example.exam.repositories.RoleRepository;
import com.example.exam.repositories.UserRepository;
import com.example.exam.services.IRoomService;
import com.example.exam.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IUserService iUserService;
    @Autowired
    IRoomService iRoomService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/add")
    public String addUser(){
        roleRepository.save(new RoleEntity(null, "ROLE_ADMIN"));
        roleRepository.save(new RoleEntity(null, "ROLE_USER"));
        UserEntity user = new UserEntity();
        user.setUsername("Admin");
        user.setEmail("admin@cy.global.com");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRoles(Set.of(roleRepository.findByName("ROLE_ADMIN")));
        userRepository.save(user);
        return "";
    }

    @GetMapping("/home")
    public String homePage(Model model){
        Pageable pageable = PageRequest.of(0, 8, Sort.by(Sort.Direction.ASC, "id"));
        Page<RoomDTO> roomDTOPage = iRoomService.getRoomEmpty(pageable);
        model.addAttribute("listRoom", roomDTOPage);
        model.addAttribute("pageNumber", roomDTOPage.getTotalPages());
        return "index";
    }

    @GetMapping("/sign-up")
    public String signUpPage(Model model){
        model.addAttribute("user", new UserDTO());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signupUser(UserDTO userDTO,  RedirectAttributes redirectAttributes) {
        UserDTO user = iUserService.signup(userDTO);
        if(user == null){
            redirectAttributes.addFlashAttribute("message", "Email exist");
            return "redirect:/sign-up";
        }
        return "redirect:/login";
    }

    @GetMapping("/book-room/{id}")
    public String bookRoom(Model model, @PathVariable Long id){
        model.addAttribute("bookRoom", new RoomBookDTO());
//        model.addAttribute("user")
        return "book-room";
    }

//    @PostMapping("/book-room")
//    public String bookRoomAdd(RoomBookDTO roomBookDTO){
//        return "book-room";
//    }
}
