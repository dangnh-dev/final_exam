package com.example.exam.controllers;

import com.example.exam.config.ResourcesConfig;
import com.example.exam.dto.RoomDTO;
import com.example.exam.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Secured({"ROLE_ADMIN"})
@RequestMapping("/admin")
public class RoomController {
    @Autowired
    IRoomService iRoomService;
    @Autowired
    ResourcesConfig resourcesConfig;

    @GetMapping("/room")
    public String roomPage(Model model){
        model.addAttribute("listRoom", iRoomService.getAllRoom());
        return "admin/room/room-detail";
    }

    @GetMapping("/room/add")
    public String addRoomPage(Model model){
        model.addAttribute("room", new RoomDTO());
        return "admin/room/add-room";
    }

    @PostMapping("/room/add")
    public String addNewRoom(RoomDTO roomDTO, @RequestParam("roomImage") MultipartFile file){
        String imageName = resourcesConfig.uploadImage(file);
        roomDTO.setImage(imageName);
        boolean result = iRoomService.createOrUpdate(roomDTO) != null;
        return "redirect:/admin/room/add?addResult="+result;
    }

    @GetMapping("/room/edit")
    public String editProductPage(@RequestParam("id")Long id, Model model){
        RoomDTO roomDTO = iRoomService.findById(id);
        model.addAttribute("room", roomDTO);
        return "admin/room/edit-room";
    }

    @PostMapping("/room/edit")
    public String editProduct(RoomDTO roomDTO, @RequestParam("roomImage") MultipartFile file){
        if (!file.isEmpty()){
            String imageName = resourcesConfig.uploadImage(file);
            roomDTO.setImage(imageName);
        }
        boolean result = iRoomService.createOrUpdate(roomDTO) != null;
        return "redirect:/admin/room?editResult="+result;
    }

    @GetMapping("/room/delete")
    public String deleteRoom(@RequestParam("id") Long id){
        boolean result = iRoomService.delete(id);
        return "redirect:/admin/room?deleteResult="+result;
    }
}
