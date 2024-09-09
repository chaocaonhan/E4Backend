package com.example.E4Center.services;

import com.example.E4Center.Responses.ChucVuResponse;
import com.example.E4Center.dtos.ChucVuDTO;
import com.example.E4Center.models.ChucVu;
import com.example.E4Center.models.KhoaHoc;
import com.example.E4Center.models.LoaiChucVu;
import com.example.E4Center.repositories.ChucVuRepository;
import com.example.E4Center.repositories.LoaiChucVuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChucVuService {


    private final ChucVuRepository chucVuRepository;
    private final LoaiChucVuRepository loaiChucVuRepository;
    private final ModelMapper modelMapper;

    public List<ChucVuResponse> getAllChucVu() {
        return chucVuRepository.findAll().stream().map(chucVu -> {
            return ChucVuResponse.builder()
                    .machucvu(chucVu.getMachucvu())
                    .tenchucvu(chucVu.getTenchucvu())
                    .luong(chucVu.getLuong())
                    .maloaichucvu(chucVu.getLoaiChucvu().getMaloaichucvu())
                    .build();
        }).collect(Collectors.toList());
    }

    public Optional<ChucVu> getChucVuById(int id) {
        return chucVuRepository.findById(id);
    }

    public ChucVu createChucVu(ChucVuDTO chucVuDTO) {
        LoaiChucVu existingLoaiChucVu = loaiChucVuRepository.findById(chucVuDTO.getMaloaichucvu()).get();

        ChucVu chucVu = ChucVu.builder()
                .tenchucvu(chucVuDTO.getTenchucvu())
                .luong(chucVuDTO.getLuong())
                .loaiChucvu(existingLoaiChucVu)
                .build();
        return chucVuRepository.save(chucVu);
    }

    public ChucVu updateChucVu(int id, ChucVuDTO chucVuDTO) {
        LoaiChucVu existingLoaiChucVu = loaiChucVuRepository.findById(chucVuDTO
        .getMaloaichucvu()).get();
        ChucVu existingChucVu = chucVuRepository.findById(id).get();

        existingChucVu.setTenchucvu(chucVuDTO.getTenchucvu());
        existingChucVu.setLuong(chucVuDTO.getLuong());
        existingChucVu.setLoaiChucvu(existingLoaiChucVu);

        return chucVuRepository.save(existingChucVu);

    }

    public void deleteChucVu(int id) {
        chucVuRepository.deleteById(id);
    }
}
