package com.example.E4Center.Responses;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LopHocResponse {
    private Long malop;
    private String tenlophoc;
    private Date ngaykhaigiang;
    private String thoigianhoc;
    private String thuhoc;
    private String cahoc;
    private String tenkhoahoc;
    private List<NguoiDungInLopHocResponse> hocvien;
    private List<NguoiDungInLopHocResponse> giangVien;
}
