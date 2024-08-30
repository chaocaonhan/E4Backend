package com.example.E4Center.services;

import com.example.E4Center.dtos.KhoaHocDTO;
import com.example.E4Center.models.KhoaHoc;
import com.example.E4Center.repositories.KhoaHocRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KhoaHocService implements IKhoaHocService {

    private final KhoaHocRepository khoaHocRepository;

    @Override
    public KhoaHoc getKhoaHocById(long MaKhoaHoc) {
        return khoaHocRepository.findById(MaKhoaHoc)
                .orElseThrow(() -> new RuntimeException("KhoaHoc not found"));
    }

    @Override
    public List<KhoaHoc> getAllKhoaHoc() {
        return khoaHocRepository.findAll();
    }

    @Override
    public KhoaHoc createKhoaHoc(KhoaHocDTO khoaHocDTO) {
        KhoaHoc khoaHoc = KhoaHoc
                .builder()
                .tenkhoahoc(khoaHocDTO.getTenkhoahoc())
                .hocphi(khoaHocDTO.getHocphi())
                .build();
        return khoaHocRepository.save(khoaHoc);
    }

    @Override
    public KhoaHoc updateKhoaHoc(long MaKhoaHoc, KhoaHocDTO khoaHocDTO) {
        KhoaHoc existingKhoaHoc = getKhoaHocById(MaKhoaHoc);
        existingKhoaHoc.setTenkhoahoc(khoaHocDTO.getTenkhoahoc());
        existingKhoaHoc.setHocphi(khoaHocDTO.getHocphi());
        khoaHocRepository.save(existingKhoaHoc);
        return existingKhoaHoc;
    }

    @Override
    public void deleteKhoaHoc(long MaKhoaHoc) {
        khoaHocRepository.deleteById(MaKhoaHoc);
    }
}
