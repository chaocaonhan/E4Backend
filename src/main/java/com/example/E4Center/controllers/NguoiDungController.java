package com.example.E4Center.controllers;


import com.example.E4Center.Responses.NguoiDungResponse;
import com.example.E4Center.dtos.NguoiDungDTO;
import com.example.E4Center.models.NguoiDung;
import com.example.E4Center.services.ChucVuService;
import com.example.E4Center.services.NguoiDungService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/nguoidung")

@RequiredArgsConstructor
public class NguoiDungController {
    private final NguoiDungService nguoidungService;

    @PostMapping()
    public ResponseEntity<?> createNguoiDung(
            @Valid @RequestBody NguoiDungDTO nguoiDungDTO,
            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        nguoidungService.createNguoiDung(nguoiDungDTO);
        return ResponseEntity.ok(nguoiDungDTO);
    }

    @GetMapping("")
    public  ResponseEntity<List<NguoiDungResponse>> getAllNguoiDung() {
        List<NguoiDungResponse> responseList = nguoidungService.getAllNguoiDung();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NguoiDung> getNguoiDungById(@PathVariable long id) {
        NguoiDung nguoiDung = nguoidungService.getNguoiDungById(id);
        return ResponseEntity.ok(nguoiDung);
    }

    @GetMapping("/getAllHocSinh")
    public ResponseEntity<List<NguoiDungResponse>> getNguoiDungSinhVien() {
        // Lấy tất cả người dùng từ service
        List<NguoiDungResponse> allNguoiDung = nguoidungService.getAllNguoiDung();

        // Lọc những người dùng có tên chức vụ chứa "sinh viên", không phân biệt chữ hoa chữ thường
        List<NguoiDungResponse> sinhVienList = allNguoiDung.stream()
                .filter(nguoiDung -> nguoiDung.getTenchucvu().toLowerCase().contains("học viên"))
                .collect(Collectors.toList());

        return ResponseEntity.ok(sinhVienList);
    }

    @GetMapping("/getAllGiaoVien")
    public ResponseEntity<List<NguoiDungResponse>> getNguoiDungGiaoVien() {
        // Lấy tất cả người dùng từ service
        List<NguoiDungResponse> allNguoiDung = nguoidungService.getAllNguoiDung();

        // Lọc những người dùng có tên chức vụ chứa "sinh viên", không phân biệt chữ hoa chữ thường
        List<NguoiDungResponse> giaovienLst = allNguoiDung.stream()
                .filter(nguoiDung -> nguoiDung.getTenchucvu().toLowerCase().contains("giáo viên"))
                .collect(Collectors.toList());

        return ResponseEntity.ok(giaovienLst);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateNguoiDung(@PathVariable long id,
                                                  @RequestBody NguoiDungDTO nguoiDungDTO) {
        nguoidungService.updateNguoiDung(id, nguoiDungDTO);
        return ResponseEntity.ok("update success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNguoiDung(@PathVariable long id) {
        nguoidungService.deleteNguoiDung(id);
        return ResponseEntity.ok("delete success");
    }

    @GetMapping("/tenKhoaHoc")
    public List<NguoiDung> getNguoiDungByChucVu(@RequestParam("tenKhoaHoc") String tenKhoaHoc) {
        return nguoidungService.getUserByTenKhoaHoc(tenKhoaHoc);
    }

}
