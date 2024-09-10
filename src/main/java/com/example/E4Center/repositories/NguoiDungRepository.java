package com.example.E4Center.repositories;

import com.example.E4Center.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    @Query("SELECT nd FROM NguoiDung nd JOIN nd.chucVus cv WHERE cv.machucvu = :machucvu")
    Set<NguoiDung> findByMachucvu(int machucvu);
}
