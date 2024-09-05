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
@Table(name = "tblxacnhan")
public class XacNhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maxacnhan")
    private Long maxacnhan; // Primary key, auto-increment

    @Column(name = "hoten")
    private String hoten;

    @Column(name = "ngaysinh")
    private Date ngaysinh;

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

    @Column(name = "malop")
    private Integer malop;

    @Column(name = "maform")
    private Long maform;
}
