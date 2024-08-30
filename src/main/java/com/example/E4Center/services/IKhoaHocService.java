package com.example.E4Center.services;

import com.example.E4Center.dtos.KhoaHocDTO;
import com.example.E4Center.models.KhoaHoc;

import java.util.List;
//IKhoaHocService : iterface này sẽ liệt kê các hàm mà service sẽ thực thi, còn bên trong class KhoaHocSerVice
// sẽ thực thi cụ thể các hàm này
public interface IKhoaHocService {
    KhoaHoc createKhoaHoc(KhoaHocDTO khoaHoc);
    KhoaHoc getKhoaHocById(long MaKhoaHoc);


    KhoaHoc updateKhoaHoc(long MaKhoaHoc ,KhoaHocDTO khoaHoc);
    void deleteKhoaHoc(long MaKhoaHoc);
    List<KhoaHoc> getAllKhoaHoc();
}
