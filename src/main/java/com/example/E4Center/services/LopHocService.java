package com.example.E4Center.services;

import com.example.E4Center.Responses.LopHocResponse;
import com.example.E4Center.Responses.NguoiDungInLopHocResponse;
import com.example.E4Center.Responses.ThoiKhoaBieuRespone;
import com.example.E4Center.dtos.LopHocDTO;
import com.example.E4Center.exceptions.DataNotFoundException;
import com.example.E4Center.models.*;
import com.example.E4Center.repositories.*;
import com.example.E4Center.services.iservices.ILopHocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LopHocService implements ILopHocService {
    private final LopHocRepository lopHocRepository;
    private final KhoaHocRepository khoaHocRepository;
    private final NguoiLopHocRepository nguoiLopHocRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final ThoiKhoaBieuRepository thoiKhoaBieuRepository;
    private final PhongHocRepository phongHocRepository;

    @Override
    public LopHoc getLopHocById(long MaLop) throws Exception {
        return  lopHocRepository.findById(MaLop).orElseThrow(() -> new RuntimeException(" not found"));
    }

    @Override
    public LopHocResponse getLopHocDetails(Long malop) {
        // Fetch LopHoc entity by malop
        LopHoc lopHoc = lopHocRepository.findById(malop).orElseThrow(() -> new RuntimeException("LopHoc not found"));

        // Prepare the response
        LopHocResponse response = LopHocResponse.builder()
                .malop(lopHoc.getMalop())
                .tenlophoc(lopHoc.getTenlophoc())
                .ngaykhaigiang(lopHoc.getNgaykhaigiang())
                .thoigianhoc(lopHoc.getThoigianhoc())
                .thuhoc(lopHoc.getThuhoc())
                .cahoc(lopHoc.getCahoc())
                .tenkhoahoc(lopHoc.getKhoaHoc().getTenkhoahoc())
                .hocvien(lopHoc.getNguoiLopHocs().stream()
                        .filter(nlh -> nlh.getNguoiDung().getChucVu().getLoaiChucvu().getMaloaichucvu() == 1) // Filter students
                        .map(nlh -> new NguoiDungInLopHocResponse(
                                nlh.getNguoiDung().getHoten(),
                                nlh.getNguoiDung().getGioitinh(),
                                nlh.getNguoiDung().getSdt(),
                                nlh.getNguoiDung().getDiachi(),
                                nlh.getNguoiDung().getEmail()))
                        .collect(Collectors.toList()))
                .giangVien(lopHoc.getNguoiLopHocs().stream()
                        .filter(nlh -> nlh.getNguoiDung().getChucVu().getLoaiChucvu().getMaloaichucvu() == 2) // Filter teachers
                        .map(nlh -> new NguoiDungInLopHocResponse(
                                nlh.getNguoiDung().getHoten(),
                                nlh.getNguoiDung().getGioitinh(),
                                nlh.getNguoiDung().getSdt(),
                                nlh.getNguoiDung().getDiachi(),
                                nlh.getNguoiDung().getEmail()))
                        .collect(Collectors.toList()))
                .build();

        return response;
    }


    @Override
    public List<LopHocResponse> getAllLopHoc() {
        return lopHocRepository.findAll().stream().map(lopHoc -> {
            return LopHocResponse.builder()
                    .malop(lopHoc.getMalop())
                    .tenlophoc(lopHoc.getTenlophoc())
                    .ngaykhaigiang(lopHoc.getNgaykhaigiang())
                    .thoigianhoc(lopHoc.getThoigianhoc())
                    .thuhoc(lopHoc.getThuhoc())
                    .cahoc(lopHoc.getCahoc())
                    .tenkhoahoc(lopHoc.getKhoaHoc().getTenkhoahoc())
                    .tenphonghoc(thoiKhoaBieuRepository.findDistinctTenPhongByMaLop(lopHoc.getMalop()))
                    .hocvien(lopHoc.getNguoiLopHocs().stream()
                            .filter(nlh -> nlh.getNguoiDung().getChucVu().getMachucvu() == 1) // Filter students
                            .map(nlh -> new NguoiDungInLopHocResponse(
                                    nlh.getNguoiDung().getHoten(),
                                    nlh.getNguoiDung().getGioitinh(),
                                    nlh.getNguoiDung().getSdt(),
                                    nlh.getNguoiDung().getDiachi(),
                                    nlh.getNguoiDung().getEmail()))
                            .collect(Collectors.toList()))
                    .giangVien(lopHoc.getNguoiLopHocs().stream()
                            .filter(nlh -> nlh.getNguoiDung().getChucVu().getLoaiChucvu().getMaloaichucvu()==2) // Filter teachers
                            .map(nlh -> new NguoiDungInLopHocResponse(
                                    nlh.getNguoiDung().getHoten(),
                                    nlh.getNguoiDung().getGioitinh(),
                                    nlh.getNguoiDung().getSdt(),
                                    nlh.getNguoiDung().getDiachi(),
                                    nlh.getNguoiDung().getEmail()))
                            .collect(Collectors.toList()))




                    .build();
        }).collect(Collectors.toList());
    }



    @Override
    public ResponseEntity<String> createLopHoc(LopHocDTO lopHocDTO) throws DataNotFoundException {
        KhoaHoc existingKhoaHoc = khoaHocRepository
                .findBytenkhoahoc(lopHocDTO.getTenkhoahoc())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay khoa hoc co ma la" + lopHocDTO.getTenkhoahoc()));

        NguoiDung giaoVien = nguoiDungRepository.findNguoiDungByHoten(lopHocDTO.getGiaovien());

        LopHoc newLopHoc = LopHoc
                .builder()
                .tenlophoc(lopHocDTO.getTenlophoc())
                .ngaykhaigiang(lopHocDTO.getNgaykhaigiang())
                .thoigianhoc(lopHocDTO.getThoigianhoc())
                .thuhoc(lopHocDTO.getThuhoc())
                .cahoc(lopHocDTO.getCahoc())
                .khoaHoc(existingKhoaHoc)
                .build();

        lopHocRepository.save(newLopHoc);

        NguoiLopHoc newNguoiLopHoc = new NguoiLopHoc();
        newNguoiLopHoc.setNguoiDung(giaoVien);
        newNguoiLopHoc.setLopHoc(newLopHoc);
        newNguoiLopHoc.setDiem(null);
        newNguoiLopHoc.setTrangThai("Đang Học");
        nguoiLopHocRepository.save(newNguoiLopHoc);

        PhongHoc phongHoc = phongHocRepository.findPhongHocByTenphong(lopHocDTO.getPhonghoc());

        //lấy các thời khóa biểu đủ điều kiện
        LocalTime tgbatdau=null,tgketthuc=null;

        switch (lopHocDTO.getCahoc()) {
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
        List<Integer> thuHocList = Arrays.stream(lopHocDTO.getThuhoc().split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<ThoiKhoaBieu> lstTKB = thoiKhoaBieuRepository.getAvaibleTKB(thuHocList,tgbatdau,tgketthuc,phongHoc.getMaphong());
        for(ThoiKhoaBieu thoiKhoaBieu : lstTKB) {
            thoiKhoaBieu.setLophoc(newLopHoc);
            thoiKhoaBieuRepository.save(thoiKhoaBieu);
        }

        return ResponseEntity.ok("them thanh cong");
    }

    @Override
    public LopHoc updateLopHoc(long MaLop, LopHocDTO lopHocDTO) throws Exception {
        LopHoc existingLopHoc = getLopHocById(MaLop);
        System.out.println(lopHocDTO.getTenlophoc());
        System.out.println(lopHocDTO.getNgaykhaigiang());
        // In ra các trường khác để kiểm tra xem có bị null không
        //coppy cac thuoc tinh tu DTO -> product
        //co the su dung ModelMaper, ObjectMaper

            KhoaHoc existingKhoaHoc = khoaHocRepository
                    .findBytenkhoahoc(lopHocDTO.getTenkhoahoc())
                    .orElseThrow(() -> new DataNotFoundException(
                            "Khong tim thay khoa hoc co ma la" + lopHocDTO.getTenkhoahoc()));
            existingLopHoc.setTenlophoc(lopHocDTO.getTenlophoc());
            existingLopHoc.setNgaykhaigiang(lopHocDTO.getNgaykhaigiang());
            existingLopHoc.setThoigianhoc(lopHocDTO.getThoigianhoc());
            existingLopHoc.setThuhoc(lopHocDTO.getThuhoc());
            existingLopHoc.setKhoaHoc(existingKhoaHoc);

            return lopHocRepository.save(existingLopHoc);
    }


    @Override
    public void deleteLopHocById(long MaLop) {
        Optional<LopHoc> optionalLopHoc = lopHocRepository.findById(MaLop);
        System.out.println(optionalLopHoc);
        if (optionalLopHoc.isPresent()) {
            lopHocRepository.delete(optionalLopHoc.get());
        }
    }
}

