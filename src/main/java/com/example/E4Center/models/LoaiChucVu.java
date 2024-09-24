package com.example.E4Center.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tblloaichucvu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoaiChucVu{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maloaichucvu")
    private int maloaichucvu;

    @Column(name = "tenloaichucvu", nullable = false)
    private String tenloaichucvu;
}
