package com.example.E4Center.services.iservices;

import com.example.E4Center.dtos.TuVanDTO;
import com.example.E4Center.models.TuVan;

import java.util.List;

public interface ITuVanService {
    TuVan createTuVan(TuVanDTO tuVanDTO);
    TuVan getTuVanById(Long MaTuVan);
    List<TuVan> getAllTuVan();
    TuVan updateTuVan(long MaTuVan,TuVanDTO tuVanDTO);
    void deleteTuVan(long MaTuVan);
}
