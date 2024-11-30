package com.example.E4Center.dtos;

import lombok.*;



public class KhoaHocDoanhThuDTO {
    private String tenKhoaHoc;
    private Double doanhThu;

    // Constructor yêu cầu
    public KhoaHocDoanhThuDTO(String tenKhoaHoc, Double doanhThu) {
        this.tenKhoaHoc = tenKhoaHoc;
        this.doanhThu = doanhThu;
    }

    // Getter và Setter (nếu cần thiết)
    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public Double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(Double doanhThu) {
        this.doanhThu = doanhThu;
    }
}


