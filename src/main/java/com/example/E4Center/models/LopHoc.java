package com.example.E4Center.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="tbllophoc")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LopHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long MaLop;

    private String TenLopHoc;
    private Date NgayKhaiGiang;
    private String ThoiGianHoc;
    private String ThuHoc;

    @ManyToOne
    @JoinColumn(name = "MaKhoaHoc")
    private KhoaHoc khoaHoc;

}
