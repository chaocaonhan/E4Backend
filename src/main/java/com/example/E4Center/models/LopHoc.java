package com.example.E4Center.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tbllophoc")
public class LopHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long malop;

    private String tenlophoc;
    private Date ngaykhaigiang;
    private String thoigianhoc;
    private String thuhoc;

    @ManyToOne
    @JoinColumn(name = "makhoahoc")
    private KhoaHoc khoaHoc;

}
