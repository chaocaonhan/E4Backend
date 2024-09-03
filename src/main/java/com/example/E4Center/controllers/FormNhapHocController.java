package com.example.E4Center.controllers;

import com.example.E4Center.dtos.FormNhapHocDTO;
import com.example.E4Center.dtos.NguoiDungDTO;
import com.example.E4Center.models.FormNhapHoc;
import com.example.E4Center.services.FormNhapHocService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/formnhaphoc")
public class FormNhapHocController{
    private final FormNhapHocService formNhapHocService;

    @PostMapping()
    public ResponseEntity<?> createFormnhaphoc(@RequestBody FormNhapHocDTO formNhapHocDTO,
                                      BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);

        }
        formNhapHocService.createFormNhapHoc(formNhapHocDTO);
        return ResponseEntity.ok("Da them form thong tin "+formNhapHocDTO);
    }

    @GetMapping("")
    public ResponseEntity<List<FormNhapHoc>> getAllFormnhaphoc() {
        List<FormNhapHoc> formNhapHocList = formNhapHocService.getAllFormNhapHoc();
        return ResponseEntity.ok(formNhapHocList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormNhapHoc> getFormnhaphocById(@PathVariable long id) {
        FormNhapHoc formNhapHoc = formNhapHocService.getFormNhapHocById(id);
        return ResponseEntity.ok(formNhapHoc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateForm(@PathVariable long id,
                                                  @RequestBody FormNhapHocDTO formNhapHocDTO) {
        formNhapHocService.updateFormNhapHoc(id, formNhapHocDTO);
        return ResponseEntity.ok("update success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteForm(@PathVariable long id) {
        formNhapHocService.deleteFormNhapHoc(id);
        return ResponseEntity.ok("delete success");
    }
}
