package com.example.E4Center.controllers;


import com.example.E4Center.Responses.LopHocResponse;
import com.example.E4Center.Responses.NguoiDungResponse;
import com.example.E4Center.Responses.ThoiKhoaBieuRespone;
import com.example.E4Center.dtos.LopHocDTO;
import com.example.E4Center.models.LopHoc;
import com.example.E4Center.models.NguoiDung;
import com.example.E4Center.services.LopHocService;
import com.example.E4Center.services.ThoiKhoaBieuService;
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
    private final LopHocService lophocService;

    private final ThoiKhoaBieuService thoiKhoaBieuService;

    // API lấy thời khóa biểu theo mã lớp học
    @GetMapping("/{malop}/thoikhoabieu")
    public ResponseEntity<List<ThoiKhoaBieuRespone>> getThoiKhoaBieuByLopHoc(@PathVariable Long malop) {
        List<ThoiKhoaBieuRespone> thoiKhoaBieuList = thoiKhoaBieuService.getThoiKhoaBieuBylophoc(malop);
        if (thoiKhoaBieuList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(thoiKhoaBieuList);
    }


    //Tìm lớp học theo id
    @GetMapping("/{id}")
    public ResponseEntity<LopHoc> getLopHocById(@PathVariable long id) throws Exception {
        LopHoc newLopHoc = lophocService.getLopHocById(id);
        return ResponseEntity.ok(newLopHoc);
    }

    //lấy thông tin lớp kèm thôngg tin giảng viên và học viên
    @GetMapping("/thongtinlopkemdanhsanhhocsinhvagiangvien/{malop}")
    public LopHocResponse getLopHocDetails(@PathVariable Long malop) {
        return lophocService.getLopHocDetails(malop);
    }

   @GetMapping("")
    public ResponseEntity<List<LopHocResponse>> getAllLopHoc(
    ) {

        List<LopHocResponse> lopHocs = lophocService.getAllLopHoc();
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
            lophocService.createLopHoc(lopHocDTO);
            return ResponseEntity.ok("them lop hoc thanh cong");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLopHoc(@PathVariable long id
            ,@RequestBody LopHocDTO lopHocDTO) throws Exception {
        lophocService.updateLopHoc(id,lopHocDTO);
        return ResponseEntity.ok("Sua lop thanh cong");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLopHocById(@PathVariable long id) {
        try {
            return lophocService.deleteLopHocById(id);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete class with ID: " + id + ". Error: " + ex.getMessage());
        }
    }
}
