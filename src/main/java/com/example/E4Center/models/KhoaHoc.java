package com.example.E4Center.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tblkhoahoc")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long makhoahoc;

    @Column(name = "tenkhoahoc")
    private String tenkhoahoc;

    @Column(name = "hocphi")
    private float hocphi;
}
