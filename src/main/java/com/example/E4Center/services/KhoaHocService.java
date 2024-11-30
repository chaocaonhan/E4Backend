package com.example.E4Center.services;

import com.example.E4Center.dtos.KhoaHocDTO;
import com.example.E4Center.dtos.KhoaHocDoanhThuDTO;
import com.example.E4Center.services.iservices.IKhoaHocService;
import com.example.E4Center.models.KhoaHoc;
import com.example.E4Center.repositories.KhoaHocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                .uudai(khoaHocDTO.getUudai())
                .thoigianuudai(khoaHocDTO.getThoigianuudai())
                .build();
        return khoaHocRepository.save(khoaHoc);
    }

    @Override
    public KhoaHoc updateKhoaHoc(long MaKhoaHoc, KhoaHocDTO khoaHocDTO) {
        KhoaHoc existingKhoaHoc = getKhoaHocById(MaKhoaHoc);
        existingKhoaHoc.setTenkhoahoc(khoaHocDTO.getTenkhoahoc());
        existingKhoaHoc.setHocphi(khoaHocDTO.getHocphi());
        existingKhoaHoc.setUudai(khoaHocDTO.getUudai());
        existingKhoaHoc.setThoigianuudai(khoaHocDTO.getThoigianuudai());
        khoaHocRepository.save(existingKhoaHoc);
        return existingKhoaHoc;
    }

    @Override
    public void deleteKhoaHoc(long MaKhoaHoc) {
        khoaHocRepository.deleteById(MaKhoaHoc);
    }

    public List<KhoaHocDoanhThuDTO> getDoanhThuKhoaHoc() {
        return khoaHocRepository.getDoanhThuKhoaHoc();
    }

    public List<Map<String, Object>> getSoLuongHocVienMoiKhoaHoc() {
        List<Object[]> results = khoaHocRepository.getSoLuongHocVienMoiKhoaHoc();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("tenKhoaHoc", result[0]);
            map.put("soLuongHocVien", result[1]);
            response.add(map);
        }

        return response;
    }

}
