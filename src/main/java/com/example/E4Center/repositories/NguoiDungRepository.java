package com.example.E4Center.repositories;

import com.example.E4Center.Responses.ThoiKhoaBieuRespone;
import com.example.E4Center.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    @Query("SELECT nd FROM NguoiDung nd WHERE nd.chucVu.tenchucvu LIKE %:tenKhoaHoc%")
    List<NguoiDung> findByTenKhoaHoc(@Param("tenKhoaHoc") String tenKhoaHoc);

    NguoiDung findNguoiDungByHoten(String hoten);
    NguoiDung findByTendangnhap(String tendangnhap);



}

