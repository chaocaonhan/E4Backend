package com.example.E4Center.repositories;

import com.example.E4Center.Responses.ThoiKhoaBieuRespone;
import com.example.E4Center.models.PhongHoc;
import com.example.E4Center.models.ThoiKhoaBieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public interface ThoiKhoaBieuRepository extends JpaRepository<ThoiKhoaBieu, Long> {
    List<ThoiKhoaBieu> findByLophoc_Malop(Long malop);


    @Query(value = "SELECT DISTINCT p.tenphong " +
            "FROM PhongHoc p " +
            "JOIN ThoiKhoaBieu tkb ON p.maphong = tkb.phonghoc.maphong " +
            "WHERE tkb.thuhoc IN :thuHoc " +  // Tham số thứ học
            "AND tkb.lophoc IS NULL " +
            "AND tkb.tgbatdau = :tgbatdau " +
            "AND tkb.tgketthuc = :tgketthuc " +
            "GROUP BY p.tenphong, tkb.phonghoc.maphong " +
            "HAVING COUNT(DISTINCT tkb.thuhoc) = (SELECT COUNT(DISTINCT thuhoc) FROM ThoiKhoaBieu WHERE thuhoc IN :thuHoc)",
            nativeQuery = false)  // Nếu bạn viết JPQL, thì nativeQuery là false
    List<String> findPhongHocWithConditions(@Param("thuHoc") List<Integer> thuHoc,
                                            @Param("tgbatdau") LocalTime tgbatdau,
                                            @Param("tgketthuc") LocalTime tgketthuc);


    @Query("SELECT tkb FROM ThoiKhoaBieu tkb " +
            "WHERE tkb.thuhoc IN :thuHoc " +  // Tham số thứ học
            "AND tkb.lophoc IS NULL " +  // Lớp học chưa được gán
            "AND tkb.tgbatdau = :tgbatdau " +  // Thời gian bắt đầu trùng khớp
            "AND tkb.tgketthuc = :tgketthuc " +  // Thời gian kết thúc trùng khớp
            "AND tkb.phonghoc.maphong = :maphonghoc")  // Điều kiện phòng học
    List<ThoiKhoaBieu> getAvaibleTKB(@Param("thuHoc") List<Integer> thuHoc,
                                                  @Param("tgbatdau") LocalTime tgbatdau,
                                                  @Param("tgketthuc") LocalTime tgketthuc,
                                                  @Param("maphonghoc") Long maphonghoc);


}
