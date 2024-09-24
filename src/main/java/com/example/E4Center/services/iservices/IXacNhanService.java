package com.example.E4Center.services.iservices;

import com.example.E4Center.dtos.XacNhanDTO;
import com.example.E4Center.models.XacNhan;

import java.util.List;

public interface IXacNhanService {
    XacNhan createXacNhan(XacNhanDTO xacNhanDTO);
    XacNhan getXacNhanById(Long MaXacNhan);
    List<XacNhan> getAllXacNhan();
    XacNhan updateXacNhan(long MaXacNhan, XacNhanDTO xacNhanDTO);
    void deleteXacNhan(long MaXacNhan);
}
