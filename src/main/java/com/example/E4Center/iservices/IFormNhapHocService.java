package com.example.E4Center.iservices;

import com.example.E4Center.dtos.FormNhapHocDTO;
import com.example.E4Center.models.FormNhapHoc;

import java.util.List;

public interface IFormNhapHocService {
    FormNhapHoc createFormNhapHoc(FormNhapHocDTO formNhapHocDTO);
    FormNhapHoc getFormNhapHocById(long maForm);
    List<FormNhapHoc> getAllFormNhapHoc();
    FormNhapHoc updateFormNhapHoc(long maForm, FormNhapHocDTO formNhapHocDTO);
    void deleteFormNhapHoc(long maForm);
}

