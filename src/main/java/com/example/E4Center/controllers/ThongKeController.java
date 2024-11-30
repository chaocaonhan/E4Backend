package com.example.E4Center.controllers;


import com.example.E4Center.dtos.KhoaHocDoanhThuDTO;
import com.example.E4Center.services.KhoaHocService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/thongke")
@RequiredArgsConstructor
public class ThongKeController {
    @Autowired
    private KhoaHocService khoaHocService;

    @GetMapping("/doanhthu")
    public ResponseEntity<List<KhoaHocDoanhThuDTO>> getDoanhThuKhoaHoc() {
        List<KhoaHocDoanhThuDTO> doanhThuKhoaHoc = khoaHocService.getDoanhThuKhoaHoc();
        return ResponseEntity.ok(doanhThuKhoaHoc);
    }

}
