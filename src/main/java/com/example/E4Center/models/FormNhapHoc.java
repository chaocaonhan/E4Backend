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
@Table(name = "tblformnhaphoc")
public class FormNhapHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maform")
    private Long maform; // Khóa chính, tự động tăng

    @Column(name = "hoten")
    private String hoten;

    @Column(name = "ngaysinh")
    private Date ngaysinh;

    @Column(name="tenkhoahoc")
    private String tenkhoahoc;

    @Column(name = "gioitinh")
    private String gioitinh;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "diachi")
    private String diachi;

    @Column(name = "email")
    private String email;

    @Column(name = "ngaygui")
    private Date ngaygui;

    @Column(name = "trangthai")
    private String trangthai;

}
