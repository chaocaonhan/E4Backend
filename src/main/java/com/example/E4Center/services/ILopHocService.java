package com.example.E4Center.services;

import com.example.E4Center.dtos.LopHocDTO;
import com.example.E4Center.models.LopHoc;

import java.util.List;

public interface ILopHocService {
    LopHoc getLopHocById(long MaLop);
    List<LopHoc> getAllLopHoc();
    LopHoc createLopHoc(LopHocDTO LopHoc);
    void deleteLopHocById(long MaLop);
    LopHoc updateLopHoc(long MaLop, LopHocDTO LopHoc);
}
