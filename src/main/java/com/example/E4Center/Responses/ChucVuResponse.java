package com.example.E4Center.Responses;

import com.example.E4Center.models.LoaiChucVu;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChucVuResponse {
    private  int machucvu;
    private String tenchucvu;
    private BigDecimal luong;
    private int maloaichucvu;
}
