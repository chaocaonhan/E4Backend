package com.example.E4Center.repositories;

import com.example.E4Center.models.LopHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface LopHocRepository extends JpaRepository<LopHoc, Long> {
    LopHoc findBytenlophoc(String tenLop);
}
