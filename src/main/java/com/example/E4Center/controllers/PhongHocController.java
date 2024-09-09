package com.example.E4Center.controllers;

import com.example.E4Center.models.PhongHoc;
import com.example.E4Center.services.PhongHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/phonghoc")
public class PhongHocController {

    @Autowired
    private PhongHocService phongHocService;

    @GetMapping
    public ResponseEntity<List<PhongHoc>> getAllPhongHocs() {
        List<PhongHoc> phongHocs = phongHocService.getAllPhongHocs();
        return new ResponseEntity<>(phongHocs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhongHoc> getPhongHocById(@PathVariable Integer id) {
        Optional<PhongHoc> phongHoc = phongHocService.getPhongHocById(id);
        return phongHoc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PhongHoc> createPhongHoc(@RequestBody PhongHoc phongHoc) {
        PhongHoc createdPhongHoc = phongHocService.createPhongHoc(phongHoc);
        return new ResponseEntity<>(createdPhongHoc, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhongHoc> updatePhongHoc(@PathVariable Integer id, @RequestBody PhongHoc phongHoc) {
        PhongHoc updatedPhongHoc = phongHocService.updatePhongHoc(id, phongHoc);
        return updatedPhongHoc != null ? ResponseEntity.ok(updatedPhongHoc) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhongHoc(@PathVariable Integer id) {
        phongHocService.deletePhongHoc(id);
        return ResponseEntity.noContent().build();
    }
}
