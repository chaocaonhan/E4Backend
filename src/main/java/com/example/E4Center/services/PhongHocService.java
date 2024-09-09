package com.example.E4Center.services;

import com.example.E4Center.models.PhongHoc;
import com.example.E4Center.repositories.PhongHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhongHocService {

    @Autowired
    private PhongHocRepository phongHocRepository;

    public List<PhongHoc> getAllPhongHocs() {
        return phongHocRepository.findAll();
    }

    public Optional<PhongHoc> getPhongHocById(Integer id) {
        return phongHocRepository.findById(id);
    }

    public PhongHoc createPhongHoc(PhongHoc phongHoc) {
        PhongHoc newPhongHoc = new PhongHoc();
        newPhongHoc.setTenphong(phongHoc.getTenphong());
        return phongHocRepository.save(newPhongHoc);
    }

    public PhongHoc updatePhongHoc(Integer id, PhongHoc phongHoc) {
        if (phongHocRepository.existsById(id)) {
            phongHoc.setTenphong(phongHoc.getTenphong());
            return phongHocRepository.save(phongHoc);
        }
        return null; // Or throw an exception if preferred
    }

    public void deletePhongHoc(Integer id) {
        phongHocRepository.deleteById(id);
    }
}
