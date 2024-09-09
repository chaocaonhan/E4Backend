package com.example.E4Center.dtos;

import com.example.E4Center.models.LoaiChucVu;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ChucVuDTO {
    private String tenchucvu;
    private BigDecimal luong;
    private int maloaichucvu;
}
