package com.example.E4Center.dtos;

import com.example.E4Center.models.PhongHoc;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Data
@Builder
public class ThoiKhoaBieuDTO {
    private Integer thuhoc;
    private String cahoc;
    private LocalTime tgbatdau;
    private LocalTime tgketthuc;
    private PhongHoc phonghoc;
}
