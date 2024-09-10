package com.example.E4Center.services;

import com.example.E4Center.dtos.FormNhapHocDTO;
import com.example.E4Center.iservices.IFormNhapHocService;
import com.example.E4Center.models.FormNhapHoc;
import com.example.E4Center.repositories.FormNhapHocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FormNhapHocService implements IFormNhapHocService {

    private final FormNhapHocRepository formNhapHocRepository;
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
        existingForm.setHoten(formNhapHocDTO.getHoten());
        existingForm.setNgaysinh(formNhapHocDTO.getNgaysinh());
        existingForm.setGioitinh(formNhapHocDTO.getGioitinh());
        existingForm.setSdt(formNhapHocDTO.getSdt());
        existingForm.setTenkhoahoc(formNhapHocDTO.getTenkhoahoc());
        existingForm.setDiachi(formNhapHocDTO.getDiachi());
        existingForm.setEmail(formNhapHocDTO.getEmail());
        existingForm.setEmail(formNhapHocDTO.getEmail());
        existingForm.setNgaygui(formNhapHocDTO.getNgaygui());
        existingForm.setTrangthai(formNhapHocDTO.getTrangthai());

        formNhapHocRepository.save(existingForm);
        return existingForm;
    }

    @Override
    public void deleteFormNhapHoc(long maForm) {
        formNhapHocRepository.deleteById(maForm);
    }
}
