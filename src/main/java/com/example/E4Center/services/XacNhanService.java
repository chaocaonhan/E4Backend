package com.example.E4Center.services;

import com.example.E4Center.dtos.XacNhanDTO;
import com.example.E4Center.models.*;
import com.example.E4Center.repositories.ChucVuRepository;
import com.example.E4Center.repositories.NguoiDungRepository;
import com.example.E4Center.repositories.NguoiLopHocRepository;
import com.example.E4Center.services.iservices.IXacNhanService;
import com.example.E4Center.repositories.XacNhanRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class XacNhanService implements IXacNhanService {

    private final XacNhanRepository xacNhanRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final NguoiLopHocRepository nguoiLopHocRepository;
    private final ModelMapper mapper;
    private final ChucVuRepository chucVuRepository;
    private final LopHocService lopHocService;


    @Override
    public XacNhan getXacNhanById(Long MaXacNhan) {
        return xacNhanRepository.findById(MaXacNhan)
                .orElseThrow(()->new RuntimeException("Khong tim thay"));
    }

    @Override
    public List<XacNhan> getAllXacNhan() {
        return xacNhanRepository.findAll();
    }

    @Override
    public XacNhan updateXacNhan(long MaXacNhan, XacNhanDTO xacNhanDTO) {
        XacNhan existingXacNhan = getXacNhanById(MaXacNhan);
        String oldTrangThai = existingXacNhan.getTrangthai(); // Get the old status
        mapper.map(xacNhanDTO, existingXacNhan);

        LopHoc lopHoc = lopHocService.getLopHocByTenLop(xacNhanDTO.getTenlophoc());
        existingXacNhan.setLopHoc(lopHoc);


        // Check if trangthai changed from "Chờ Xét Duyệt" to "Hoàn thành"
        if (oldTrangThai.equals("Chờ Xác Nhận") && existingXacNhan.getTrangthai().equals("Hoàn Thành")) {
            // Create new NguoiDung entry
            NguoiDung newNguoiDung = new NguoiDung();
            newNguoiDung.setHoten(existingXacNhan.getHoten());
            newNguoiDung.setNgaysinh(existingXacNhan.getNgaysinh());
            newNguoiDung.setGioitinh(existingXacNhan.getGioitinh());
            newNguoiDung.setSdt(existingXacNhan.getSdt());
            newNguoiDung.setDiachi(existingXacNhan.getDiachi());
            newNguoiDung.setEmail(existingXacNhan.getEmail());
            String username = Normalizer.normalize(existingXacNhan.getHoten(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "") // Loại bỏ dấu
                    .toLowerCase()            // Chuyển thành chữ thường
                    .replaceAll("\\s+", "");  // Loại bỏ khoảng trắng
            newNguoiDung.setTendangnhap(username);
            newNguoiDung.setMatkhau("12345"); // Default password
            newNguoiDung.setChucVu(chucVuRepository.getReferenceById(1)); // Set default MaChucVu
            nguoiDungRepository.save(newNguoiDung); // Save NguoiDung

            // Create new NguoiLopHoc entry
            NguoiLopHoc newNguoiLopHoc = new NguoiLopHoc();
            newNguoiLopHoc.setNguoiDung(newNguoiDung); // Associate with the new user
            newNguoiLopHoc.setLopHoc(existingXacNhan.getLopHoc());
            newNguoiLopHoc.setDiemkiemtra(null);
            newNguoiLopHoc.setDiemcuoiki(null);

            Date today = new Date();
            Date thoigianUuDai = existingXacNhan.getLopHoc().getKhoaHoc().getThoigianuudai(); // Lấy thời gian ưu đãi

            if (thoigianUuDai != null && today.before(thoigianUuDai)) {
                int uudai = Integer.parseInt(existingXacNhan.getLopHoc().getKhoaHoc().getUudai());
                newNguoiLopHoc.setUudai(uudai);
            } else {
                newNguoiLopHoc.setUudai(0);
            }
            newNguoiLopHoc.setTrangThai("Đang Học"); // Default status
            nguoiLopHocRepository.save(newNguoiLopHoc); // Save NguoiLopHoc
        }
        xacNhanRepository.save(existingXacNhan);
        return existingXacNhan;
    }


    @Override
    public void deleteXacNhan(long MaXacNhan) {
        XacNhan existingXacNhan = getXacNhanById(MaXacNhan);
        xacNhanRepository.delete(existingXacNhan);
    }
}
