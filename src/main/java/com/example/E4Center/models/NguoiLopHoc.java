package com.example.E4Center.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tblnguoi_lophoc")
public class NguoiLopHoc {

    @EmbeddedId
    private NguoiLopHocId id = new NguoiLopHocId();

    @ManyToOne
    @MapsId("maNguoiDung")
    @JoinColumn(name = "manguoidung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @MapsId("maLop")
    @JoinColumn(name = "malop")
    private LopHoc lopHoc;


    @Column(name = "diemkiemtra")
    private Float diemkiemtra;

    @Column(name = "diemcuoiki")
    private Float diemcuoiki;

    @Column(name = "uudai")
    private Integer uudai;

    @Column(name = "trangthai", length = 100)
    private String trangThai;
}
