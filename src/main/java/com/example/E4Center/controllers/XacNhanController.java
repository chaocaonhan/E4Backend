package com.example.E4Center.controllers;


import com.example.E4Center.dtos.XacNhanDTO;
import com.example.E4Center.models.XacNhan;
import com.example.E4Center.services.XacNhanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/xacnhan")
public class XacNhanController {

    private final XacNhanService xacNhanService;

    @PostMapping("")
    public ResponseEntity<?> createXacNhan(@RequestBody XacNhanDTO xacNhanDTO,
                                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);

        }
        xacNhanService.createXacNhan(xacNhanDTO);
        return ResponseEntity.ok(xacNhanDTO);
    }

    @GetMapping("")
    public ResponseEntity<List<XacNhan>> getAllXacNhan() {
        List<XacNhan> xacNhanList = xacNhanService.getAllXacNhan();
        return ResponseEntity.ok(xacNhanList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<XacNhan> getXacNhanById(@PathVariable long id) {
        XacNhan xacNhan = xacNhanService.getXacNhanById(id);
        return ResponseEntity.ok(xacNhan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateXacNhan(@PathVariable long id, @RequestBody XacNhanDTO xacNhanDTO) {
        xacNhanService.updateXacNhan(id, xacNhanDTO);
        return ResponseEntity.ok("update thanh cong ! TT moi "+xacNhanDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteXacNhan(@PathVariable long id) {
        xacNhanService.deleteXacNhan(id);
        return ResponseEntity.ok("delete thanh cong ! TT moi "+id);
    }

}
