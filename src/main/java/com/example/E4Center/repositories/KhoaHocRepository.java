package com.example.E4Center.repositories;

import com.example.E4Center.models.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Long > {
    Optional<KhoaHoc> findBytenkhoahoc(String tenKhoaHoc);
}
