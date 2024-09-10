package com.example.E4Center.Responses;

import com.example.E4Center.models.ChucVu;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Builder
@Getter
@Setter
@Data
public class NguoiDungResponse {
    private Long manguoidung;
    private String hoten;
    private Date ngaysinh;
    private String gioitinh;
    private String sdt;
    private String diachi;
    private String email;
    private String tendangnhap;
    private String matkhau;
    private String tenchucvu;
}
