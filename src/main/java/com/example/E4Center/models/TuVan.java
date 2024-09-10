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
@Table(name = "tbltuvan")
public class TuVan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matuvan;

    private String hoten;
    private Date ngaygui;
    private String sdt;
    private String nghenghiep;
    private String cosogannhat;
    private String cauhoituvan;
    private String trangthai;
}
