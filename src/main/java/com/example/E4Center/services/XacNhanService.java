package com.example.E4Center.services;

import com.example.E4Center.dtos.XacNhanDTO;
import com.example.E4Center.models.ChucVu;
import com.example.E4Center.models.NguoiDung;
import com.example.E4Center.models.NguoiLopHoc;
import com.example.E4Center.repositories.ChucVuRepository;
import com.example.E4Center.repositories.NguoiDungRepository;
import com.example.E4Center.repositories.NguoiLopHocRepository;
import com.example.E4Center.services.iservices.IXacNhanService;
import com.example.E4Center.models.XacNhan;
import com.example.E4Center.repositories.XacNhanRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class XacNhanService implements IXacNhanService {

    private final XacNhanRepository xacNhanRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final NguoiLopHocRepository nguoiLopHocRepository;
    private final ModelMapper mapper;
    private final ChucVuRepository chucVuRepository;

    @Override
    public XacNhan createXacNhan(XacNhanDTO xacNhanDTO) {
        XacNhan xacNhan = new XacNhan();
        mapper.map(xacNhanDTO, xacNhan);
        return xacNhanRepository.save(xacNhan);
    }

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


        // Check if trangthai changed from "Chờ Xét Duyệt" to "Hoàn thành"
        if (oldTrangThai.equals("Chờ Xác Nhận") && existingXacNhan.getTrangthai().equals("Hoàn thành")) {
            // Create new NguoiDung entry
            NguoiDung newNguoiDung = new NguoiDung();
            newNguoiDung.setHoten(existingXacNhan.getHoten());
            newNguoiDung.setNgaysinh(existingXacNhan.getNgaysinh());
            newNguoiDung.setGioitinh(existingXacNhan.getGioitinh());
            newNguoiDung.setSdt(existingXacNhan.getSdt());
            newNguoiDung.setDiachi(existingXacNhan.getDiachi());
            newNguoiDung.setEmail(existingXacNhan.getEmail());
            String username = existingXacNhan.getHoten().toLowerCase().replaceAll("\\s+", "");
            newNguoiDung.setTendangnhap(username); // Convert to lowercase and remove spaces
            newNguoiDung.setMatkhau("12345"); // Default password
            newNguoiDung.setChucVu(chucVuRepository.getReferenceById(1)); // Set default MaChucVu
            nguoiDungRepository.save(newNguoiDung); // Save NguoiDung

            // Create new NguoiLopHoc entry
            NguoiLopHoc newNguoiLopHoc = new NguoiLopHoc();
            newNguoiLopHoc.setNguoiDung(newNguoiDung); // Associate with the new user
            newNguoiLopHoc.setLopHoc(existingXacNhan.getLopHoc()); // Assuming you want to keep the same class
            newNguoiLopHoc.setDiem(null); // Default score is null
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
