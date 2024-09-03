package com.example.E4Center.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tblnguoidung")

public class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manguoidung")
    private Long manguoidung; // Khóa chính, tự động tăng


    @Column(name = "hoten")
    private String hoten;

    @Column(name="ngaysinh")
    private Date ngaysinh;

    @Column(name="gioitinh")
    private String gioitinh;

    @Column(name="sdt")
    private String sdt;

    @Column(name="diachi")
    private String diachi;

    @Column(name="email")
    private String email;

    @Column(name="tendangnhap")
    private String tendangnhap;

    @Column(name="matkhau")
    private String matkhau;
}
