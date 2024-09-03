package com.example.E4Center.services;

import com.example.E4Center.dtos.NguoiDungDTO;
import com.example.E4Center.iservices.INguoiDungService;
import com.example.E4Center.models.NguoiDung;
import com.example.E4Center.repositories.NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NguoiDungService implements INguoiDungService {
    private final NguoiDungRepository nguoiDungRepository;

    @Override
    public NguoiDung createNguoiDung(NguoiDungDTO nguoiDungDTO) {
            NguoiDung nguoiDung = NguoiDung
                    .builder()
                    .hoten(nguoiDungDTO.getHoten())
                    .ngaysinh(nguoiDungDTO.getNgaysinh())
                    .gioitinh(nguoiDungDTO.getGioitinh())
                    .sdt(nguoiDungDTO.getSdt())
                    .diachi(nguoiDungDTO.getDiachi())
                    .email(nguoiDungDTO.getEmail())
                    .tendangnhap(nguoiDungDTO.getTendangnhap())
                    .matkhau(nguoiDungDTO.getMatkhau())
                    .build();

            return nguoiDungRepository.save(nguoiDung);
    }

    @Override
    public NguoiDung getNguoiDungById(Long MaNguoiDung) {
        return nguoiDungRepository.findById(MaNguoiDung)
                .orElseThrow(()->new RuntimeException("khong tim thay ng dung"));
    }

    @Override
    public List<NguoiDung> getAllNguoiDung() {
        return nguoiDungRepository.findAll();
    }

    @Override
    public NguoiDung updateNguoiDung(long MaNguoiDung, NguoiDungDTO nguoiDungDTO) {
        NguoiDung existingNguoiDung = getNguoiDungById(MaNguoiDung);
        existingNguoiDung.setHoten(nguoiDungDTO.getHoten());
        existingNguoiDung.setNgaysinh(nguoiDungDTO.getNgaysinh());
        existingNguoiDung.setGioitinh(nguoiDungDTO.getGioitinh());
        existingNguoiDung.setSdt(nguoiDungDTO.getSdt());
        existingNguoiDung.setDiachi(nguoiDungDTO.getDiachi());
        existingNguoiDung.setEmail(nguoiDungDTO.getEmail());
        existingNguoiDung.setTendangnhap(nguoiDungDTO.getTendangnhap());
        existingNguoiDung.setMatkhau(nguoiDungDTO.getMatkhau());

        nguoiDungRepository.save(existingNguoiDung);
        return existingNguoiDung;
    }

    @Override
    public void deleteNguoiDung(long MaNguoiDung) {
        nguoiDungRepository.deleteById(MaNguoiDung);
    }
}
