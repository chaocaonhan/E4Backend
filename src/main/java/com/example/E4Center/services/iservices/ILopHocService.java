package com.example.E4Center.services.iservices;

import com.example.E4Center.Responses.LopHocResponse;
import com.example.E4Center.dtos.LopHocDTO;
import com.example.E4Center.exceptions.DataNotFoundException;
import com.example.E4Center.models.LopHoc;

import java.util.List;

public interface ILopHocService {
    LopHoc getLopHocById(long MaLop) throws Exception;
    List<LopHocResponse> getAllLopHoc();
    LopHoc createLopHoc(LopHocDTO LopHoc) throws DataNotFoundException;;
    void deleteLopHocById(long MaLop);
    LopHoc updateLopHoc(long MaLop, LopHocDTO LopHoc) throws Exception;
    public LopHocResponse getLopHocDetails(Long malop);
}
