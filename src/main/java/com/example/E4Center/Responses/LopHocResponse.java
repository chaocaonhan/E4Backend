package com.example.E4Center.Responses;

import lombok.*;

import java.util.Date;
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
    private String tenkhoahoc;
}
