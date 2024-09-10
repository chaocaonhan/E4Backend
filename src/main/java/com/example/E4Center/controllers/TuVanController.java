package com.example.E4Center.controllers;

import com.example.E4Center.dtos.TuVanDTO;
import com.example.E4Center.models.TuVan;
import com.example.E4Center.services.TuVanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tuvan")
public class TuVanController {

    private final TuVanService tuVanService;

    @PostMapping("")
    public ResponseEntity<?> createTuVan(@RequestBody TuVanDTO tuVanDTO,
                                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);

        }
        tuVanService.createTuVan(tuVanDTO);
        return ResponseEntity.ok(tuVanDTO);
    }

    @GetMapping("")
    public ResponseEntity<List<TuVan>> getAllTuVan() {
        List<TuVan> tuVanList = tuVanService.getAllTuVan();
        return ResponseEntity.ok(tuVanList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TuVan> getTuVanById(@PathVariable long id) {
        TuVan tuVan = tuVanService.getTuVanById(id);
        return ResponseEntity.ok(tuVan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTuVan(@PathVariable long id, @RequestBody TuVanDTO tuVanDTO) {
        tuVanService.updateTuVan(id, tuVanDTO);
        return ResponseEntity.ok("update thanh cong ! TT moi "+tuVanDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTuVan(@PathVariable long id) {
        tuVanService.deleteTuVan(id);
        return ResponseEntity.ok("delete thanh cong ! TT moi "+id);
    }

}
