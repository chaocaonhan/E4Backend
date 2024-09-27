package com.example.E4Center.services;

import com.example.E4Center.Responses.ThoiKhoaBieuRespone;
import com.example.E4Center.dtos.ThoiKhoaBieuDTO;
import com.example.E4Center.services.iservices.IThoiKhoaBieuService;
import com.example.E4Center.models.ThoiKhoaBieu;
import com.example.E4Center.repositories.ThoiKhoaBieuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThoiKhoaBieuService implements IThoiKhoaBieuService {
    private final ThoiKhoaBieuRepository thoiKhoaBieuRepository;
    private final ModelMapper modelMapper;



    public List<ThoiKhoaBieuRespone> getThoiKhoaBieuBylophoc(Long malop) {
        List<ThoiKhoaBieu> thoiKhoaBieus = thoiKhoaBieuRepository.findByLophoc_Malop(malop);

        modelMapper.typeMap(ThoiKhoaBieu.class, ThoiKhoaBieuRespone.class).addMapping(
                src -> src.getPhonghoc().getTenphong(), ThoiKhoaBieuRespone::setTenphonghoc);

        return thoiKhoaBieus.stream()
                .map(thoiKhoaBieu -> modelMapper.map(thoiKhoaBieu, ThoiKhoaBieuRespone.class))
                .collect(Collectors.toList());
    }

    public ThoiKhoaBieuRespone getThoiKhoaById(long MaThoiKhoaBieu) {
        ThoiKhoaBieu tkb = thoiKhoaBieuRepository.findById(MaThoiKhoaBieu)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thời khóa biểu với ID: " + MaThoiKhoaBieu));

        // Tạo ThoiKhoaBieuRespone từ dữ liệu đã tìm thấy
        ThoiKhoaBieuRespone response = new ThoiKhoaBieuRespone(
                tkb.getMatkb(),
                tkb.getThuhoc(),
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
                    .tgbatdau(thoiKhoaBieu.getTgbatdau())
                    .tgketthuc(thoiKhoaBieu.getTgketthuc())
                    .tenphonghoc(thoiKhoaBieu.getPhonghoc().getTenphong())
            .build();
        }).collect(Collectors.toList());
    }


    @Override
    public ThoiKhoaBieu createThoiKhoaBieu(ThoiKhoaBieuDTO thoiKhoaBieuDTO) {
        ThoiKhoaBieu thoiKhoaBieu = ThoiKhoaBieu.builder()
                .thuhoc(thoiKhoaBieuDTO.getThuhoc())
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
