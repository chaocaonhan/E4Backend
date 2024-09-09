package com.example.E4Center.Responses;

import lombok.*;

import java.time.LocalTime;
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThoiKhoaBieuRespone {
    private Long matkb;
    private Integer thuhoc;
    private String cahoc;
    private LocalTime tgbatdau;
    private LocalTime tgketthuc;
    private String phonghoc;
}
