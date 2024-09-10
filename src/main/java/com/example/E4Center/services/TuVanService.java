package com.example.E4Center.services;

import com.example.E4Center.dtos.TuVanDTO;
import com.example.E4Center.iservices.ITuVanService;
import com.example.E4Center.models.TuVan;
import com.example.E4Center.repositories.TuVanRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TuVanService implements ITuVanService {

    private final TuVanRepository tuVanRepository;
    private final ModelMapper mapper;
    @Override
    public TuVan createTuVan(TuVanDTO tuVanDTO) {
        TuVan tuVan = new TuVan();
        mapper.map(tuVanDTO, tuVan);
        return tuVanRepository.save(tuVan);
    }

    @Override
    public TuVan getTuVanById(Long MaTuVan) {
        return tuVanRepository.findById(MaTuVan)
                .orElseThrow(()->new RuntimeException("Khong tim thay"));
    }

    @Override
    public List<TuVan> getAllTuVan() {
        return tuVanRepository.findAll();
    }

    @Override
    public TuVan updateTuVan(long MaTuVan, TuVanDTO tuVanDTO) {
        TuVan existingTuVan = getTuVanById(MaTuVan);
        existingTuVan.setHoten(tuVanDTO.getHoten());
        existingTuVan.setNgaygui(tuVanDTO.getNgaygui());
        existingTuVan.setSdt(tuVanDTO.getSdt());
        existingTuVan.setNghenghiep(tuVanDTO.getNghenghiep());
        existingTuVan.setCosogannhat(tuVanDTO.getCosogannhat());
        existingTuVan.setCauhoituvan(tuVanDTO.getCauhoituvan());
        existingTuVan.setTrangthai(tuVanDTO.getTrangthai());
        tuVanRepository.save(existingTuVan);


        return tuVanRepository.save(existingTuVan);
    }

    @Override
    public void deleteTuVan(long MaTuVan) {
        TuVan existingTuVan = getTuVanById(MaTuVan);
        tuVanRepository.delete(existingTuVan);
    }
}
