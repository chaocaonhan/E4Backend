package com.example.E4Center.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "tbltkb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThoiKhoaBieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matkb;

    private Integer thuhoc;

    @Column(length = 10)
    private String cahoc;

    private LocalTime tgbatdau;

    private LocalTime tgketthuc;

    @ManyToOne
    @JoinColumn(name = "maphong", referencedColumnName = "maphong")
    private PhongHoc phonghoc;
}
