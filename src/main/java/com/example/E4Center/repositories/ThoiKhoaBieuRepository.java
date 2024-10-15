package com.example.E4Center.repositories;

import com.example.E4Center.Responses.ThoiKhoaBieuRespone;
import com.example.E4Center.models.NguoiDung;
import com.example.E4Center.models.PhongHoc;
import com.example.E4Center.models.ThoiKhoaBieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ThoiKhoaBieuRepository extends JpaRepository<ThoiKhoaBieu, Long> {
    List<ThoiKhoaBieu> findByLophoc_Malop(Long malop);



@Query(value = "SELECT DISTINCT p.tenphong " +
        "FROM PhongHoc p " +
        "JOIN ThoiKhoaBieu tkb ON p.maphong = tkb.phonghoc.maphong " +
        "WHERE tkb.thuhoc IN :thuHoc " +
        "AND tkb.tgbatdau = :tgbatdau " +
        "AND tkb.tgketthuc = :tgketthuc " +
        "AND tkb.lophoc IS NULL " +  // Điều kiện thêm vào để kiểm tra thời khoá biểu trống
        "GROUP BY p.tenphong, tkb.phonghoc.maphong " +
        "HAVING COUNT(DISTINCT tkb.thuhoc) = (SELECT COUNT(DISTINCT thuhoc) FROM ThoiKhoaBieu WHERE thuhoc IN :thuHoc)",
        nativeQuery = false)
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

    @Query("SELECT DISTINCT tkb.phonghoc.tenphong FROM ThoiKhoaBieu tkb WHERE tkb.lophoc.malop = :malop")
    Optional<String> findDistinctTenPhongByMaLop(@Param("malop") Long malop);


    // Truy vấn để tìm những giáo viên không có lịch trong các khoảng thời gian của các thứ học được chỉ định
    @Query("SELECT nd FROM NguoiDung nd WHERE nd.chucVu.tenchucvu LIKE '%Giáo Viên%' AND " +
            "NOT EXISTS (SELECT tkb FROM ThoiKhoaBieu tkb WHERE tkb.lophoc IN " +
            "(SELECT nl.lopHoc FROM NguoiLopHoc nl WHERE nl.nguoiDung = nd) " +
            "AND tkb.thuhoc IN :thuHoc " +
            "AND ((tkb.tgbatdau = :tgbatdau) " +
            "AND (tkb.tgketthuc = :tgketthuc)))")
    List<NguoiDung> findGiaoVienRanh(@Param("thuHoc") List<Integer> thuHoc,
                                     @Param("tgbatdau") LocalTime tgbatdau,
                                     @Param("tgketthuc") LocalTime tgketthuc);

    //set mã lớp thành null
    @Modifying
    @Query("UPDATE ThoiKhoaBieu t SET t.lophoc = NULL WHERE t.lophoc.malop = :malop")
    void updateMalopToNull(@Param("malop") Long malop);

    @Query("SELECT tkb FROM ThoiKhoaBieu tkb JOIN tkb.lophoc lh JOIN lh.nguoiLopHocs nlh WHERE nlh.nguoiDung.manguoidung = :maNguoiDung")
    List<ThoiKhoaBieu> findScheduleByUserId(@Param("maNguoiDung") Long maNguoiDung);
}