package com.example.E4Center.controllers;

import com.example.E4Center.Responses.ThoiKhoaBieuRespone;
import com.example.E4Center.dtos.ThoiKhoaBieuDTO;
import com.example.E4Center.models.NguoiDung;
import com.example.E4Center.models.ThoiKhoaBieu;
import com.example.E4Center.services.ThoiKhoaBieuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/phonghoctuongung")
    public List<String> getAvailableRooms(@RequestParam(value = "thuHoc") String thuHoc,
                                          @RequestParam String caHoc) {
        LocalTime tgbatdau=null,tgketthuc=null;

        switch (caHoc) {
            case "Sáng":
                tgbatdau = LocalTime.parse("07:00");
                tgketthuc = LocalTime.parse("10:00");
                break;
            case "Chiều":
                tgbatdau = LocalTime.parse("13:00");
                tgketthuc = LocalTime.parse("16:00");
                break;
            case "Tối":
                tgbatdau = LocalTime.parse("18:00");
                tgketthuc = LocalTime.parse("21:00");
                break;
        }

        // Tách chuỗi thành danh sách số nguyên
        List<Integer> thuHocList = Arrays.stream(thuHoc.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return thoikhoabieuService.getAvailableRooms(thuHocList, tgbatdau, tgketthuc);
    }

    @GetMapping("/laytkbtheophongvalopvaca")
    public List<ThoiKhoaBieu> getAvailableTKB(@RequestParam(value = "thuHoc") String thuHoc,
                                          @RequestParam String caHoc,
                                        @RequestParam Long maphonghoc) {
        LocalTime tgbatdau=null,tgketthuc=null;

        switch (caHoc) {
            case "Sáng":
                tgbatdau = LocalTime.parse("07:00");
                tgketthuc = LocalTime.parse("10:00");
                break;
            case "Chiều":
                tgbatdau = LocalTime.parse("13:00");
                tgketthuc = LocalTime.parse("16:00");
                break;
            case "Tối":
                tgbatdau = LocalTime.parse("18:00");
                tgketthuc = LocalTime.parse("21:00");
                break;
        }

        // Tách chuỗi thành danh sách số nguyên
        List<Integer> thuHocList = Arrays.stream(thuHoc.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return thoikhoabieuService.getAvaibleTKB(thuHocList, tgbatdau, tgketthuc,maphonghoc);
    }

    @GetMapping("/tim-giao-vien-ranh")
    public List<NguoiDung> timGiaoVienRanh(@RequestParam(value = "thuHoc") String thuHoc, @RequestParam String caHoc) {

        // Tách chuỗi thành danh sách số nguyên
        List<Integer> thuHocLst = Arrays.stream(thuHoc.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return thoikhoabieuService.timGiaoVienRanh(thuHocLst, caHoc);
    }
}
