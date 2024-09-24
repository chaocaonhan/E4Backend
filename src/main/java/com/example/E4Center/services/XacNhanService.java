package com.example.E4Center.services;

import com.example.E4Center.dtos.XacNhanDTO;
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
    private final ModelMapper mapper;
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
        mapper.map(xacNhanDTO, existingXacNhan);
        xacNhanRepository.save(existingXacNhan);

        return existingXacNhan;
    }

    @Override
    public void deleteXacNhan(long MaXacNhan) {
        XacNhan existingXacNhan = getXacNhanById(MaXacNhan);
        xacNhanRepository.delete(existingXacNhan);
    }
}
