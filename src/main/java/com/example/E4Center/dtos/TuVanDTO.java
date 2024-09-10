package com.example.E4Center.dtos;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TuVanDTO {
    private Long matuvan;

    private String hoten;
    private Date ngaygui;
    private String sdt;
    private String nghenghiep;
    private String cosogannhat;
    private String cauhoituvan;
    private String trangthai;
}
