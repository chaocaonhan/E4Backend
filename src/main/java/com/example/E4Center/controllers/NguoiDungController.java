package com.example.E4Center.controllers;


import com.example.E4Center.Responses.NguoiDungResponse;
import com.example.E4Center.dtos.KhoaHocDTO;
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
import java.util.Set;

@RestController
@RequestMapping("api/v1/nguoidung")

@RequiredArgsConstructor
public class NguoiDungController {
    private final NguoiDungService nguoidungService;

    @PostMapping()
    public ResponseEntity<?> createKhoahoc(
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
    public  ResponseEntity<List<NguoiDung>> getAllNguoiDung() {
        List<NguoiDung> nguoiDungList = nguoidungService.getAllNguoiDung();
        return ResponseEntity.ok(nguoiDungList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NguoiDung> getNguoiDungById(@PathVariable long id) {
        NguoiDung nguoiDung = nguoidungService.getNguoiDungById(id);
        return ResponseEntity.ok(nguoiDung);
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

    @GetMapping("/chucvu")
    public Set<NguoiDungResponse> getNguoiDungByMachucvu(@RequestParam int machucvu) {
        return nguoidungService.getNguoiDungByMachucvu(machucvu);
    }


}
