package com.example.E4Center.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LopHocDTO {
    @NotBlank(message = "chua nhap ten lop")
    private String TenLopHoc;
    @NotNull(message = "chua nhap ngay khai giang")
    private String NgayKhaiGiang;
    private String ThoiGianHoc;
    private String ThuHoc;
    private String MaKhoaHoc;
}
