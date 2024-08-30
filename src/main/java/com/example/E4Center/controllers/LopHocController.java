package com.example.E4Center.controllers;


import com.example.E4Center.dtos.LopHocDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lophoc")
public class LopHocController {

    @GetMapping("")
    public ResponseEntity<String> getAllLopHoc(
    ) {
        return ResponseEntity.ok("lay tat ca cac lop");
    }

    @PostMapping("")
    public ResponseEntity<?> insertLopHoc(
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
            return ResponseEntity.ok("them lop hoc thanh cong");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<?> updateLopHoc(@PathVariable("id") long MaLop){
        return ResponseEntity.ok("Sua lop thanh cong");
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> getLopHocById(@PathVariable("id") long MaLop) {
        return ResponseEntity.ok("lop hoc co ma la :"+MaLop);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLopHocById(@PathVariable("id") long MaLop) {
        return ResponseEntity.ok("xoa lop co ma :"+MaLop);
    }

}
