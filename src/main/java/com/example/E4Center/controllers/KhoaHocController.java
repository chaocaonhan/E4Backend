package com.example.E4Center.controllers;

import com.example.E4Center.dtos.KhoaHocDTO;
import com.example.E4Center.models.KhoaHoc;
import com.example.E4Center.services.KhoaHocService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/khoahoc")
//@Validated

//dependency Injectction
@RequiredArgsConstructor
public class KhoaHocController {

    private final KhoaHocService khoaHocService;


    // lấy theo id
    @GetMapping("/{id}")
    public ResponseEntity<KhoaHoc> getKhoaHocById(@PathVariable long id) {
        // Gọi phương thức từ đối tượng đã tiêm vào
        KhoaHoc newKhoaHoc = khoaHocService.getKhoaHocById(id);
        return ResponseEntity.ok(newKhoaHoc);
    }

    //  lấy tất cả khóa học.
    @GetMapping("")//http://localhost:8088/api/v1/khoahoc
    public ResponseEntity<List<KhoaHoc>> getAllKhoahoc(
    ){
        List<KhoaHoc> khoaHocs = khoaHocService.getAllKhoaHoc();
        return ResponseEntity.ok(khoaHocs);
    }

    @PostMapping("")
    //nếu tham số truyền vào là object ? => data transfer object = request object
    public ResponseEntity<?> createKhoahoc(
            @Valid @RequestBody KhoaHocDTO khoaHocDTO,
            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);

        }
        khoaHocService.createKhoaHoc(khoaHocDTO);
        return ResponseEntity.ok(khoaHocDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateKhoahoc(@PathVariable long id,
                                                @RequestBody KhoaHocDTO khoaHocDTO
    ) {
        khoaHocService.updateKhoaHoc(id, khoaHocDTO);
        return ResponseEntity.ok("update khoa hoc"+id+"Thanh cong");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKhoahoc(@PathVariable long id) {
        khoaHocService.deleteKhoaHoc(id);
        return ResponseEntity.ok("delete khoa hoc with id ="+ id +" thanh cong");
    }
}
