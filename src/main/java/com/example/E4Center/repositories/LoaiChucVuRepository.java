package com.example.E4Center.repositories;

import com.example.E4Center.models.LoaiChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiChucVuRepository extends JpaRepository<LoaiChucVu, Integer> {
}
