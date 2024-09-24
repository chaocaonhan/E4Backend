package com.example.E4Center.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblchucvu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChucVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machucvu")
    private int machucvu;

    @Column(name = "tenchucvu", nullable = false)
    private String tenchucvu;

    @Column(name = "luong", nullable = false)
    private BigDecimal luong;

    @ManyToOne
    @JoinColumn(name = "maloaichucvu", referencedColumnName = "maloaichucvu")
    private LoaiChucVu loaiChucvu;
}
