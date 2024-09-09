package com.example.E4Center.controllers;

import com.example.E4Center.Responses.ThoiKhoaBieuRespone;
import com.example.E4Center.dtos.ThoiKhoaBieuDTO;
import com.example.E4Center.models.ThoiKhoaBieu;
import com.example.E4Center.services.ThoiKhoaBieuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/thoikhoabieu")
@RequiredArgsConstructor
public class ThoiKhoaBieuController {

    private final ThoiKhoaBieuService thoikhoabieuService;

    @GetMapping()
    public ResponseEntity<List<ThoiKhoaBieuRespone>> getThoiKhoaBieu() {
        List<ThoiKhoaBieuRespone> tkbs = thoikhoabieuService.getAllThoiKhoa();
        return ResponseEntity.ok(tkbs);
    }

    @GetMapping("/{id}")
    public ThoiKhoaBieuRespone getById(@PathVariable long id) {
        return thoikhoabieuService.getThoiKhoaById(id);
    }

//    @PostMapping()
//    public ThoiKhoaBieu createTKB(@RequestBody ThoiKhoaBieuDTO thoiKhoaBieuDTO){
//        return thoikhoabieuService.createThoiKhoaBieu(thoiKhoaBieuDTO);
//    }


}
