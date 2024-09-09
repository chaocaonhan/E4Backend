package com.example.E4Center.controllers;

import com.example.E4Center.Responses.ChucVuResponse;
import com.example.E4Center.dtos.ChucVuDTO;
import com.example.E4Center.models.ChucVu;
import com.example.E4Center.services.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chucvu")
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping
    public ResponseEntity<List<ChucVuResponse>> getAllChucVu() {
        List<ChucVuResponse> chucVuRspList = chucVuService.getAllChucVu();
        return ResponseEntity.ok(chucVuRspList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChucVu> getChucVuById(@PathVariable int id) {
        Optional<ChucVu> chucVu = chucVuService.getChucVuById(id);
        return chucVu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ChucVu> createChucVu(@RequestBody ChucVuDTO chucVuDTO) {
        ChucVu created = chucVuService.createChucVu(chucVuDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChucVu> updateChucVu(@PathVariable int id, @RequestBody ChucVuDTO chucVuDTO) {
        ChucVu updated = chucVuService.updateChucVu(id, chucVuDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChucVu(@PathVariable int id) {
        chucVuService.deleteChucVu(id);
        return ResponseEntity.ok("xoa thanh cong");
    }
}
