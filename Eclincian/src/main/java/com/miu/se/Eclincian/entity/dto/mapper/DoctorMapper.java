package com.miu.se.Eclincian.entity.dto.mapper;

import com.miu.se.Eclincian.entity.Doctor;
import com.miu.se.Eclincian.entity.dto.response.DoctorResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    @Autowired
    private ModelMapper modelMapper;
    public DoctorResponseDTO convertToDTO(Doctor doctor){
        DoctorResponseDTO doctorResponseDTO =modelMapper.map(doctor, DoctorResponseDTO.class);
        doctorResponseDTO.setFName(doctor.getUser().getFName());
        return  doctorResponseDTO;
    }

}
