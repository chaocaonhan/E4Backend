package com.example.E4Center.repositories;

import com.example.E4Center.models.PhongHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongHocRepository extends JpaRepository<PhongHoc, Integer> {
    public PhongHoc findPhongHocByTenphong(String tenphong);
}
