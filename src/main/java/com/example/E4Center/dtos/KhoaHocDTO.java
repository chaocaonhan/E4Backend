package com.example.E4Center.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhoaHocDTO {
    private String tenkhoahoc;
    @Min(value = 0, message = "Hoc phi phai lon hon hoac bang 0")
    private int hocphi;
    private String uudai;
    private String tguudai;
}
