package com.example.E4Center.services;

import com.example.E4Center.Responses.LopHocResponse;
import com.example.E4Center.dtos.LopHocDTO;
import com.example.E4Center.exceptions.DataNotFoundException;
import com.example.E4Center.iservices.ILopHocService;
import com.example.E4Center.models.KhoaHoc;
import com.example.E4Center.models.LopHoc;
import com.example.E4Center.repositories.KhoaHocRepository;
import com.example.E4Center.repositories.LopHocRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LopHocService implements ILopHocService {
    private final LopHocRepository lopHocRepository;
    private final KhoaHocRepository khoaHocRepository;
    private final ModelMapper modelMapper;

    @Override
    public LopHoc getLopHocById(long MaLop) throws Exception {
        return  lopHocRepository.findById(MaLop).orElseThrow(() -> new RuntimeException(" not found"));
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
                    .tenkhoahoc(lopHoc.getKhoaHoc().getTenkhoahoc())
                    .build();
        }).collect(Collectors.toList());
    }


    @Override
    public LopHoc createLopHoc(LopHocDTO lopHocDTO) throws DataNotFoundException {
        KhoaHoc existingKhoaHoc = khoaHocRepository
                .findById(lopHocDTO.getMakhoahoc())
                .orElseThrow(() -> new DataNotFoundException(
                        "Khong tim thay khoa hoc co ma la" + lopHocDTO.getMakhoahoc()));

        LopHoc newLopHoc = LopHoc
                .builder()
                .tenlophoc(lopHocDTO.getTenlophoc())
                .ngaykhaigiang(lopHocDTO.getNgaykhaigiang())
                .thoigianhoc(lopHocDTO.getThoigianhoc())
                .thuhoc(lopHocDTO.getThuhoc())
                .khoaHoc(existingKhoaHoc)
                .build();
        return lopHocRepository.save(newLopHoc);
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
                    .findById(lopHocDTO.getMakhoahoc())
                    .orElseThrow(() -> new DataNotFoundException(
                            "Khong tim thay khoa hoc co ma la" + lopHocDTO.getMakhoahoc()));

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

