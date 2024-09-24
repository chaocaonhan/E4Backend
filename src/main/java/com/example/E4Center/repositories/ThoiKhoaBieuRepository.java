package com.example.E4Center.repositories;

import com.example.E4Center.Responses.ThoiKhoaBieuRespone;
import com.example.E4Center.models.ThoiKhoaBieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThoiKhoaBieuRepository extends JpaRepository<ThoiKhoaBieu, Long> {
    List<ThoiKhoaBieu> findByLophoc_Malop(Long malop);

}
