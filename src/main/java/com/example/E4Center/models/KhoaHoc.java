package com.example.E4Center.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    //nếu thêm đoạn dưới thì khi getkhóa học, kết quả sẽ hiển thị các lớp học thuộc khóa đó, và khi
    //get lớp học, sẽ bị lỗi lặp vô tận
//    @OneToMany(mappedBy = "khoaHoc",cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<LopHoc> lopHocLst = new ArrayList<>();
}
