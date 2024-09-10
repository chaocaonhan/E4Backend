package com.example.E4Center.services;

import com.example.E4Center.Responses.NguoiDungResponse;
import com.example.E4Center.dtos.NguoiDungDTO;
import com.example.E4Center.iservices.INguoiDungService;
import com.example.E4Center.models.ChucVu;
import com.example.E4Center.models.NguoiDung;
import com.example.E4Center.repositories.ChucVuRepository;
import com.example.E4Center.repositories.NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new RuntimeException("khong tim thay ng dung"));
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

    @Override
    public List<NguoiDung> getALLGiaoVien() {
        return null;
    }

    public Set<NguoiDungResponse> getNguoiDungByMachucvu(int machucvu) {
        return nguoiDungRepository.findByMachucvu(machucvu).stream().map(nguoiDung -> {
            // Lấy tên của tất cả chức vụ
            String tenChucVu = nguoiDung.getChucVus().stream()
                    .map(ChucVu::getTenchucvu)
                    .collect(Collectors.joining(", "));

            return NguoiDungResponse.builder()
                    .manguoidung(nguoiDung.getManguoidung())
                    .hoten(nguoiDung.getHoten())
                    .ngaysinh(nguoiDung.getNgaysinh())
                    .gioitinh(nguoiDung.getGioitinh())
                    .sdt(nguoiDung.getSdt())
                    .diachi(nguoiDung.getDiachi())
                    .email(nguoiDung.getEmail())
                    .tendangnhap(nguoiDung.getTendangnhap())
                    .matkhau(nguoiDung.getMatkhau())
                    .tenchucvu(tenChucVu) // Gán tên chức vụ ở đây
                    .build();
        }).collect(Collectors.toSet());
    }


    @Override
    public List<NguoiDung> getAllHocVien() {
        return List.of();
    }
}
