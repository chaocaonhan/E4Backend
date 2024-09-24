package com.example.E4Center.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TuVanDTO {
    private Long matuvan;

    private String hoten;
    private LocalDate ngaygui;
    private String sdt;
    private String nghenghiep;
    private String cauhoituvan;
    private String trangthai;
}
