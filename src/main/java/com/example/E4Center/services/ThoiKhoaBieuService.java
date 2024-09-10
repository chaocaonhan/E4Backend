package com.example.E4Center.services;

import com.example.E4Center.Responses.ThoiKhoaBieuRespone;
import com.example.E4Center.dtos.ThoiKhoaBieuDTO;
import com.example.E4Center.iservices.IThoiKhoaBieuService;
import com.example.E4Center.models.ThoiKhoaBieu;
import com.example.E4Center.repositories.ThoiKhoaBieuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThoiKhoaBieuService implements IThoiKhoaBieuService {
    private final ThoiKhoaBieuRepository thoiKhoaBieuRepository;

    public ThoiKhoaBieuRespone getThoiKhoaById(long MaThoiKhoaBieu) {
        ThoiKhoaBieu tkb = thoiKhoaBieuRepository.findById(MaThoiKhoaBieu)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thời khóa biểu với ID: " + MaThoiKhoaBieu));

        // Tạo ThoiKhoaBieuRespone từ dữ liệu đã tìm thấy
        ThoiKhoaBieuRespone response = new ThoiKhoaBieuRespone(
                tkb.getMatkb(),
                tkb.getThuhoc(),
                tkb.getCahoc(),
                tkb.getTgbatdau(),
                tkb.getTgketthuc(),
                tkb.getPhonghoc().getTenphong()
        );

        return response;  // Trả về response
    }

    @Override
    public List<ThoiKhoaBieuRespone> getAllThoiKhoa() {
        return thoiKhoaBieuRepository.findAll().stream().map(thoiKhoaBieu -> {
            return new ThoiKhoaBieuRespone().builder()
                    .thuhoc(thoiKhoaBieu.getThuhoc())
                    .cahoc(thoiKhoaBieu.getCahoc())
                    .tgbatdau(thoiKhoaBieu.getTgbatdau())
                    .tgketthuc(thoiKhoaBieu.getTgketthuc())
                    .phonghoc(thoiKhoaBieu.getPhonghoc().getTenphong())
            .build();
        }).collect(Collectors.toList());
    }


    @Override
    public ThoiKhoaBieu createThoiKhoaBieu(ThoiKhoaBieuDTO thoiKhoaBieuDTO) {
        ThoiKhoaBieu thoiKhoaBieu = ThoiKhoaBieu.builder()
                .thuhoc(thoiKhoaBieuDTO.getThuhoc())
                .cahoc(thoiKhoaBieuDTO.getCahoc())
                .tgbatdau(thoiKhoaBieuDTO.getTgbatdau())
                .tgketthuc(thoiKhoaBieuDTO.getTgketthuc())
                .phonghoc(thoiKhoaBieuDTO.getPhonghoc())
                .build();
        return thoiKhoaBieuRepository.save(thoiKhoaBieu);
    }

    @Override
    public ThoiKhoaBieu updateThoiKhoaBieu(long MaThoiKhoaBieu, ThoiKhoaBieuDTO thoiKhoaBieuDTO) {
        return null;
    }

    @Override
    public void deleteThoiKhoaBieu(long MaThoiKhoaBieu) {
        thoiKhoaBieuRepository.deleteById(MaThoiKhoaBieu);
    }
}
