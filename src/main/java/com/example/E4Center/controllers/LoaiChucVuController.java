package com.example.E4Center.controllers;

import com.example.E4Center.dtos.LoaiChucVuDTO;
import com.example.E4Center.models.LoaiChucVu;
import com.example.E4Center.services.LoaiChucVuService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/loaichucvu")
public class LoaiChucVuController {
    private final LoaiChucVuService loaiChucVuService;

    @GetMapping
    public ResponseEntity<List<LoaiChucVu>> getAllLoaiChucVu() {
        List<LoaiChucVu> loaiChucVuList = loaiChucVuService.getAllLoaiChucVu();
        return ResponseEntity.ok(loaiChucVuList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoaiChucVu> getLoaiChucVuById(@PathVariable int id) {
        Optional<LoaiChucVu> loaiChucVu = loaiChucVuService.getLoaiChucVuById(id);
        return loaiChucVu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LoaiChucVu> createLoaiChucVu(@RequestBody LoaiChucVuDTO loaiChucVuDTO) {
        LoaiChucVu created = loaiChucVuService.createLoaiChucVu(loaiChucVuDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoaiChucVu> updateLoaiChucVu(@PathVariable int id, @RequestBody LoaiChucVuDTO loaiChucVuDTO) {
        LoaiChucVu updated = loaiChucVuService.updateLoaiChucVu(id, loaiChucVuDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoaiChucVu(@PathVariable int id) {
        loaiChucVuService.deleteLoaiChucVu(id);
        return ResponseEntity.ok("đã xóa thành công loại chức vụ "+ id);
    }
}
