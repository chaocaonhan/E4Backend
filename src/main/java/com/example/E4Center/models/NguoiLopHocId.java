package com.example.E4Center.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class NguoiLopHocId implements Serializable {
    private Integer maNguoiDung;
    private Integer maLop;
}

