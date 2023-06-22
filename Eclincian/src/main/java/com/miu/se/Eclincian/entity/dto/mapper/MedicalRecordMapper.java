package com.miu.se.Eclincian.entity.dto.mapper;

import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.entity.dto.response.MedicalRecordResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicalRecordMapper {
    @Autowired
    private ModelMapper modelMapper;

    public MedicalRecordResponseDTO convertToDTO(MedicalRecord medicalRecord){
        MedicalRecordResponseDTO medicalRecordResponseDTO= modelMapper.map(medicalRecord, MedicalRecordResponseDTO.class);
        medicalRecordResponseDTO.setDoctorName(medicalRecord.getDoctor().getUser().getFName());
        medicalRecordResponseDTO.setPatientName(medicalRecord.getPatient().getUser().getFName());
        return medicalRecordResponseDTO;
    }
}
