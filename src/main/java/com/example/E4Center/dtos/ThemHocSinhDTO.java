package com.example.E4Center.dtos;

public class ThemHocSinhDTO {
    private NguoiDungDTO nguoiDungDTO;
    private Long malop;

    // Getters và setters
    public NguoiDungDTO getNguoiDungDTO() {
        return nguoiDungDTO;
    }

    public void setNguoiDungDTO(NguoiDungDTO nguoiDungDTO) {
        this.nguoiDungDTO = nguoiDungDTO;
    }

    public Long getMaLop() {
        return malop;
    }

    public void setMalop(Long ml) {
        this.malop = ml;
    }
}

