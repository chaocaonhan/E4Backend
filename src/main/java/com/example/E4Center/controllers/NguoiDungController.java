package com.example.E4Center.controllers;


import com.example.E4Center.Responses.LoginResponse;
import com.example.E4Center.Responses.NguoiDungResponse;
import com.example.E4Center.Responses.ThoiKhoaBieuCaNhanResponse;
import com.example.E4Center.dtos.NguoiDungDTO;
import com.example.E4Center.dtos.NguoiDungDangNhapDTO;
import com.example.E4Center.dtos.ThemHocSinhDTO;
import com.example.E4Center.exceptions.DataNotFoundException;
import com.example.E4Center.models.LopHoc;
import com.example.E4Center.models.NguoiDung;
import com.example.E4Center.models.NguoiLopHoc;
import com.example.E4Center.repositories.NguoiDungRepository;
import com.example.E4Center.repositories.NguoiLopHocRepository;
import com.example.E4Center.services.LopHocService;
import com.example.E4Center.services.NguoiDungService;
import com.example.E4Center.services.ThoiKhoaBieuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/nguoidung")

@RequiredArgsConstructor
public class NguoiDungController {
    private final NguoiDungService nguoidungService;
    private final NguoiDungRepository nguoiDungRepository;
    private final ThoiKhoaBieuService thoiKhoaBieuService;
    private final LopHocService lopHocService;
    private final NguoiLopHocRepository nguoiLopHocRepository;

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


@PostMapping("/themhocsinh")
public ResponseEntity<?> themHocSinhAndThemVaoLop(
        @Valid @RequestBody ThemHocSinhDTO themHocSinhDTO,
        BindingResult bindingResult) throws Exception {
    if (bindingResult.hasErrors()) {
        List<String> errorMessages = bindingResult.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(errorMessages);
    }

    NguoiDungDTO nguoiDungDTO = themHocSinhDTO.getNguoiDungDTO();
    Long malop = themHocSinhDTO.getMalop();

    NguoiDung recentAddUser = nguoidungService.createNguoiDung(nguoiDungDTO);

    LopHoc exitsLopHoc = lopHocService.getLopHocById(malop);
    if(exitsLopHoc == null) {
        throw new DataNotFoundException("Không tìm thấy lớp !");
    }

    NguoiLopHoc newNLH = new NguoiLopHoc();
    newNLH.setNguoiDung(recentAddUser);
    newNLH.setLopHoc(exitsLopHoc);
    newNLH.setDiem(null);
    newNLH.setTrangThai("Đang Học");
    nguoiLopHocRepository.save(newNLH);

    return ResponseEntity.ok("Đã thêm học sinh vào lớp!");
}

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody NguoiDungDangNhapDTO nguoiDungDangNhapDTO) throws DataNotFoundException {
        LoginResponse response = nguoidungService.login(nguoiDungDangNhapDTO);
        return ResponseEntity.ok(response);
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


    @GetMapping("/giaovientronglich")
    public List<String> getGiaoVienTrongLich(@RequestParam("tenKhoaHoc") String tenKhoaHoc,
                                             @RequestParam(value = "thuHoc") String thuHoc,
                                             @RequestParam String caHoc){
        List<NguoiDung> giaoVienCoTrinhDoTuongUngVoiKhoa = nguoidungService.getUserByTenKhoaHoc(tenKhoaHoc);

        List<Integer> thuHocLst = Arrays.stream(thuHoc.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<NguoiDung> giaoVienTrongLich = thoiKhoaBieuService.timGiaoVienRanh(thuHocLst,caHoc);

        List<String> ketQua = new ArrayList<>();

        for (NguoiDung gv : giaoVienCoTrinhDoTuongUngVoiKhoa) {
            if (giaoVienTrongLich.contains(gv)) {
                // Giáo viên trống lịch
                ketQua.add(gv.getHoten()+ " - Trống lịch");
            } else {
                // Giáo viên trùng lịch
                ketQua.add(gv.getHoten() + " - Trùng lịch");
            }
        }
        return ketQua;
    }

    @GetMapping("/personalSchedule/{maNguoiDung}")
    public ResponseEntity<List<ThoiKhoaBieuCaNhanResponse>> getPersonalSchedule(@PathVariable Long maNguoiDung) {
        List<ThoiKhoaBieuCaNhanResponse> schedule = thoiKhoaBieuService.getScheduleForUser(maNguoiDung);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping("/settendangnhap")
    public ResponseEntity<String> updateTenDangNhap(){
        nguoidungService.updateAllUsernames();
        return ResponseEntity.ok("update success");
    }


}
