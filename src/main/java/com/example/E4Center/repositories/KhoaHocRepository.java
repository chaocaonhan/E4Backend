package com.example.E4Center.repositories;

import com.example.E4Center.dtos.KhoaHocDoanhThuDTO;
import com.example.E4Center.models.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Long > {
    Optional<KhoaHoc> findBytenkhoahoc(String tenKhoaHoc);


    @Query("SELECT new com.example.E4Center.dtos.KhoaHocDoanhThuDTO(" +
            "kh.tenkhoahoc, CAST(SUM(kh.hocphi * (1 - COALESCE(nl.uudai, 0) / 100)) AS double)) " +
            "FROM KhoaHoc kh " +
            "JOIN kh.lopHocs lh " +
            "JOIN lh.nguoiLopHocs nl " +
            "JOIN nl.nguoiDung nd " +
            "WHERE nd.chucVu.loaiChucvu.maloaichucvu = 1 " +
            "GROUP BY kh.tenkhoahoc")
    List<KhoaHocDoanhThuDTO> getDoanhThuKhoaHoc();

    @Query("SELECT kh.tenkhoahoc, COUNT(nl) " +
            "FROM KhoaHoc kh " +
            "JOIN kh.lopHocs lh " +
            "JOIN lh.nguoiLopHocs nl " +
            "JOIN nl.nguoiDung nd " +
            "WHERE nd.chucVu.loaiChucvu.maloaichucvu = 1 " +
            "GROUP BY kh.tenkhoahoc")
    List<Object[]> getSoLuongHocVienMoiKhoaHoc();

}
