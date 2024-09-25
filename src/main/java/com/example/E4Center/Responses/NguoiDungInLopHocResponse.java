package com.example.E4Center.Responses;

import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NguoiDungInLopHocResponse {
    private String hoten;
    private String gioitinh;
    private String sdt;
    private String diachi;
    private String email;
}
