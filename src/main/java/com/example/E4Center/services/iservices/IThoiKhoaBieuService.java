package com.example.E4Center.services.iservices;

import com.example.E4Center.Responses.ThoiKhoaBieuRespone;
import com.example.E4Center.dtos.ThoiKhoaBieuDTO;
import com.example.E4Center.models.ThoiKhoaBieu;

import java.util.List;

public interface IThoiKhoaBieuService {
    ThoiKhoaBieuRespone getThoiKhoaById(long MaThoiKhoaBieu);
    List<ThoiKhoaBieuRespone> getAllThoiKhoa();
    ThoiKhoaBieu createThoiKhoaBieu(ThoiKhoaBieuDTO thoiKhoaBieuDTO);
    ThoiKhoaBieu updateThoiKhoaBieu(long MaThoiKhoaBieu, ThoiKhoaBieuDTO thoiKhoaBieuDTO);
    void deleteThoiKhoaBieu(long MaThoiKhoaBieu);
}
