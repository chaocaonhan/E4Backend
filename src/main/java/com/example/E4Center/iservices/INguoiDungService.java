package com.example.E4Center.iservices;

import com.example.E4Center.dtos.NguoiDungDTO;
import com.example.E4Center.models.NguoiDung;

import java.util.List;

public interface INguoiDungService {
    NguoiDung createNguoiDung(NguoiDungDTO nguoiDungDTO);
    NguoiDung getNguoiDungById(Long MaNguoiDung);
    List<NguoiDung> getAllNguoiDung();
    NguoiDung updateNguoiDung(long MaNguoiDung,NguoiDungDTO nguoiDungDTO);
    void deleteNguoiDung(long MaNguoiDung);

    List<NguoiDung> getALLGiaoVien();
    List<NguoiDung> getAllHocVien();
}
