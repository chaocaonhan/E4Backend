package com.example.E4Center.services;

import com.example.E4Center.dtos.FormNhapHocDTO;
import com.example.E4Center.models.XacNhan;
import com.example.E4Center.repositories.XacNhanRepository;
import com.example.E4Center.services.iservices.IFormNhapHocService;
import com.example.E4Center.models.FormNhapHoc;
import com.example.E4Center.repositories.FormNhapHocRepository;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FormNhapHocService implements IFormNhapHocService {

    private final FormNhapHocRepository formNhapHocRepository;
    private final XacNhanRepository xacNhanRepository;

    @Override
    public FormNhapHoc createFormNhapHoc(FormNhapHocDTO formNhapHocDTO) {
        FormNhapHoc formNhapHoc = FormNhapHoc
                .builder()
                .hoten(formNhapHocDTO.getHoten())
                .ngaysinh(formNhapHocDTO.getNgaysinh())
                .tenkhoahoc(formNhapHocDTO.getTenkhoahoc())
                .gioitinh(formNhapHocDTO.getGioitinh())
                .sdt(formNhapHocDTO.getSdt())
                .diachi(formNhapHocDTO.getDiachi())
                .email(formNhapHocDTO.getEmail())
                .ngaygui(formNhapHocDTO.getNgaygui())
                .trangthai(formNhapHocDTO.getTrangthai())
                .build();
        return formNhapHocRepository.save(formNhapHoc);
    }

    @Override
    public FormNhapHoc getFormNhapHocById(long maForm) {
        return formNhapHocRepository.findById(maForm).orElseThrow(()->new RuntimeException("Khong tim thay"));
    }

    @Override
    public List<FormNhapHoc> getAllFormNhapHoc() {
        return formNhapHocRepository.findAll();
    }

    @Override
    public FormNhapHoc updateFormNhapHoc(long maForm, FormNhapHocDTO formNhapHocDTO) {
        FormNhapHoc existingForm = getFormNhapHocById(maForm);
        String oldStatus = existingForm.getTrangthai();

        existingForm.setHoten(formNhapHocDTO.getHoten());
        existingForm.setNgaysinh(formNhapHocDTO.getNgaysinh());
        existingForm.setGioitinh(formNhapHocDTO.getGioitinh());
        existingForm.setSdt(formNhapHocDTO.getSdt());
        existingForm.setTenkhoahoc(formNhapHocDTO.getTenkhoahoc());
        existingForm.setDiachi(formNhapHocDTO.getDiachi());
        existingForm.setEmail(formNhapHocDTO.getEmail());
        existingForm.setNgaygui(formNhapHocDTO.getNgaygui());
        existingForm.setTrangthai(formNhapHocDTO.getTrangthai());

        if(oldStatus.equals("Chờ Xét Duyệt")&&existingForm.getTrangthai().equals("Hoàn Thành")){
            XacNhan newXacNhan = new XacNhan();
            newXacNhan.setHoten(existingForm.getHoten());
            newXacNhan.setNgaysinh(existingForm.getNgaysinh());
            newXacNhan.setGioitinh(existingForm.getGioitinh());
            newXacNhan.setSdt(existingForm.getSdt());
            newXacNhan.setDiachi(existingForm.getDiachi());
            newXacNhan.setEmail(existingForm.getEmail());
            newXacNhan.setNgaygui(existingForm.getNgaygui());
            newXacNhan.setTrangthai("Chờ đóng tiền");
            newXacNhan.setLopHoc(null);
            xacNhanRepository.save(newXacNhan);
        }

        formNhapHocRepository.save(existingForm);
        return existingForm;
    }

    @Override
    public void deleteFormNhapHoc(long maForm) {
        formNhapHocRepository.deleteById(maForm);
    }
}
