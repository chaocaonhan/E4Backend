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
    @JsonProperty("tenlophoc")
    private String tenlophoc;

    @Valid
    @JsonProperty("ngaykhaigiang")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date ngaykhaigiang;

    @JsonProperty("thoigianhoc")
    private String thoigianhoc;

    @JsonProperty("thuhoc")
    private String thuhoc;

    @JsonProperty("makhoahoc")
    private Long makhoahoc;
}
