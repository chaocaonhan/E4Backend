package com.example.E4Center.repositories;

import com.example.E4Center.models.NguoiDung;
import com.example.E4Center.models.NguoiLopHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NguoiLopHocRepository extends JpaRepository<NguoiLopHoc, Integer> {
    @Query("SELECT nl.nguoiDung FROM NguoiLopHoc nl WHERE nl.lopHoc.malop = :maLop")
    List<NguoiDung> findNguoiDungByLopHoc(@Param("maLop") Long maLop);
}
