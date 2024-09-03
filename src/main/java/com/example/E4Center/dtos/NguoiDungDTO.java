package com.example.E4Center.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDungDTO {

    private String hoten;
    private Date ngaysinh;
    private String gioitinh;
    private String sdt;

    private String diachi;
    private String email;
    private String tendangnhap;
    private String matkhau;

}