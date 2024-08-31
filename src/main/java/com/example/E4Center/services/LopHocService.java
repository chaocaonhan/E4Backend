package com.example.E4Center.services;

import com.example.E4Center.dtos.LopHocDTO;
import com.example.E4Center.models.KhoaHoc;
import com.example.E4Center.models.LopHoc;
import com.example.E4Center.repositories.LopHocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LopHocService implements ILopHocService{
    private final LopHocRepository lopHocRepository;

    @Override
    public LopHoc getLopHocById(long MaLop) {
        return lopHocRepository.findById(MaLop)
                .orElseThrow(() -> new RuntimeException("LopHoc not found"));
    }

    @Override
    public List<LopHoc> getAllLopHoc() {
        return lopHocRepository.findAll();
    }

    @Override
    public LopHoc createLopHoc(LopHocDTO LopHocDTO) {
        LopHoc lopHoc = com.example.E4Center.models.LopHoc
                .builder()
                .tenlophoc(LopHocDTO.getTenlophoc())
                .ngaykhaigiang(LopHocDTO.getNgaykhaigiang())
                .thoigianhoc(LopHocDTO.getThoigianhoc())
                .thuhoc(LopHocDTO.getThuhoc())
                .build();
        return lopHocRepository.save(lopHoc);
    }

    @Override
    public void deleteLopHocById(long MaLop) {

    }

    @Override
    public LopHoc updateLopHoc(long MaLop, LopHocDTO LopHoc) {
        return null;
    }
}
