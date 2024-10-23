package com.example.E4Center.Responses;

import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThoiKhoaBieuCaNhanResponse {
    private Integer thuhoc;
    private LocalTime tgbatdau;
    private LocalTime tgketthuc;
    private String tenphonghoc;
    private String tenlop;
    private Date ngaykhaigiang;
    private String thoigianhoc;
}
