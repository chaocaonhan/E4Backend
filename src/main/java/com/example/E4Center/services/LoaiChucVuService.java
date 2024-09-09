package com.example.E4Center.services;

import com.example.E4Center.dtos.LoaiChucVuDTO;
import com.example.E4Center.models.LoaiChucVu;
import com.example.E4Center.repositories.LoaiChucVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiChucVuService {

    @Autowired
    private LoaiChucVuRepository loaiChucVuRepository;

    public List<LoaiChucVu> getAllLoaiChucVu() {
        return loaiChucVuRepository.findAll();
    }

    public Optional<LoaiChucVu> getLoaiChucVuById(int id) {
        return loaiChucVuRepository.findById(id);
    }

    public LoaiChucVu createLoaiChucVu(LoaiChucVuDTO loaiChucVuDTO) {
        LoaiChucVu loaiChucVu = LoaiChucVu.builder()
                .tenloaichucvu(loaiChucVuDTO.getTenloaichucvu())
                .build();

        return loaiChucVuRepository.save(loaiChucVu);
    }

    public LoaiChucVu updateLoaiChucVu(int id, LoaiChucVuDTO loaiChucVuDTO) {
        if (loaiChucVuRepository.existsById(id)) {
            LoaiChucVu loaiChucVu = loaiChucVuRepository.findById(id).get();
            loaiChucVu.setTenloaichucvu(loaiChucVuDTO.getTenloaichucvu());
            return loaiChucVuRepository.save(loaiChucVu);
        }
        return null;
    }

    public void deleteLoaiChucVu(int id) {
        loaiChucVuRepository.deleteById(id);
    }
}
