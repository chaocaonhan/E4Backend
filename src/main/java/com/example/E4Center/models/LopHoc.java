package com.example.E4Center.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date ngaykhaigiang;
    private String thoigianhoc;
    private String thuhoc;


    @Column(length = 10)
    private String cahoc;//    chuyển từ tkb sang

    @ManyToOne(optional=false)
    @JoinColumn(name = "makhoahoc", referencedColumnName = "makhoahoc")
    private KhoaHoc khoaHoc;

    @OneToMany(mappedBy = "lopHoc")
    private Set<NguoiLopHoc> nguoiLopHocs;


}
