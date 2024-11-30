package com.example.E4Center.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "tblkhoahoc")
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

    private String uudai;
    private Date thoigianuudai;

    @JsonIgnore
    @OneToMany(mappedBy = "khoaHoc", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LopHoc> lopHocs;

}
