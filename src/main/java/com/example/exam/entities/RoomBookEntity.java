package com.example.exam.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_room_book")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDay;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDay;
    @ManyToOne
    @JoinColumn(name = "userEntity_id")
    private UserEntity userEntity;
    @OneToOne
    @JoinColumn(name = "roomEntity_id", referencedColumnName = "id")
    private RoomEntity roomEntity;
}
