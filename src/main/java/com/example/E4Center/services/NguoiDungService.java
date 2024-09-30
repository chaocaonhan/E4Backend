package com.example.E4Center.services;

import com.example.E4Center.Responses.NguoiDungResponse;
import com.example.E4Center.dtos.NguoiDungDTO;
import com.example.E4Center.services.iservices.INguoiDungService;
import com.example.E4Center.models.ChucVu;
import com.example.E4Center.models.NguoiDung;
import com.example.E4Center.repositories.ChucVuRepository;
import com.example.E4Center.repositories.NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class NguoiDungService implements INguoiDungService {
    private final NguoiDungRepository nguoiDungRepository;
    private final ModelMapper modelMapper;
    private final ChucVuRepository chucVuRepository;

    @Override
    public NguoiDung createNguoiDung(NguoiDungDTO nguoiDungDTO) {

        ChucVu chucVu = chucVuRepository.findByTenchucvu(nguoiDungDTO.getTenchucvu());
        if (chucVu == null) {
            throw new RuntimeException("Không tìm thấy chức vụ với tên: " + nguoiDungDTO.getTenchucvu());
        }
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
                .chucVu(chucVu)
                .build();

        return nguoiDungRepository.save(nguoiDung);
    }

    @Override
    public NguoiDung getNguoiDungById(Long MaNguoiDung) {
        return nguoiDungRepository.findById(MaNguoiDung)
                .orElseThrow(() -> new RuntimeException("khong tim thay ng dung"));
    }



    @Override
    public List<NguoiDungResponse> getAllNguoiDung() {
        List<NguoiDung> nguoiDungList = nguoiDungRepository.findAll();

        return nguoiDungList.stream().map(nguoiDung -> {
            NguoiDungResponse response = modelMapper.map(nguoiDung, NguoiDungResponse.class);
            response.setTenchucvu(nguoiDung.getChucVu().getTenchucvu());
            response.setMaloaichucvu((long) nguoiDung.getChucVu().getLoaiChucvu().getMaloaichucvu());
            return response;
        }).toList();
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


    @Override
    public List<NguoiDung> getAllHocVien() {
        return List.of();
    }


    private String extractIeltsPart(String input) {
        if (input != null && input.contains("IELTS")) {
            int index = input.indexOf("IELTS");
            return input.substring(index);
        }
        return "";
    }

    public List<NguoiDung> getUserByTenKhoaHoc(String tenKhoaHoc) {
            String chuVuCanTim=extractIeltsPart(tenKhoaHoc);
            return nguoiDungRepository.findByTenKhoaHoc(chuVuCanTim);
    }
}
