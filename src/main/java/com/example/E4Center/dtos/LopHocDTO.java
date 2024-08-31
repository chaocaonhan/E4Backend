package com.example.E4Center.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LopHocDTO{

    @NotBlank(message = "chua nhap ten lop")
    @JsonProperty("TenLopHoc")
    private String tenlophoc;

    @Valid
    @JsonProperty("NgayKhaiGiang")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date ngaykhaigiang;

    @JsonProperty("ThoiGianHoc")
    private String thoigianhoc;

    @JsonProperty("ThuHoc")
    private String thuhoc;

    @JsonProperty("makhoahoc")
    private Long makhoahoc;
}
