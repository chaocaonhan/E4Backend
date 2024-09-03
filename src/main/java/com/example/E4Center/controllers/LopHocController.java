package com.example.E4Center.controllers;


import com.example.E4Center.dtos.LopHocDTO;
import com.example.E4Center.models.LopHoc;
import com.example.E4Center.services.LopHocService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lophoc")
@RequiredArgsConstructor
public class LopHocController {
    private final LopHocService lopHocService;

    //Tìm lớp học theo id
    @GetMapping("/{id}")
    public ResponseEntity<LopHoc> getLopHocById(@PathVariable long id) throws Exception {
        LopHoc newLopHoc = lopHocService.getLopHocById(id);
        return ResponseEntity.ok(newLopHoc);
    }

    @GetMapping("")
    public ResponseEntity<List<LopHoc>> getAllLopHoc(
    ) {

        List<LopHoc> lopHocs = lopHocService.getAllLopHoc();
        return ResponseEntity.ok(lopHocs);
    }

    @PostMapping("")
    public ResponseEntity<?> createLopHoc(
            @Valid @RequestBody LopHocDTO lopHocDTO,
            BindingResult bindingResult
            ){
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            lopHocService.createLopHoc(lopHocDTO);
            return ResponseEntity.ok("them lop hoc thanh cong");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<?> updateLopHoc(@PathVariable long id
            ,@RequestBody LopHocDTO lopHocDTO){
        return ResponseEntity.ok("Sua lop thanh cong");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLopHocById(@PathVariable long id) {
        try {
            lopHocService.deleteLopHocById(id);
            return ResponseEntity.ok("Deleted class with ID: " + id);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete class with ID: " + id + ". Error: " + ex.getMessage());
        }
    }
}
