package com.miu.se.Eclincian.service.implmentation;

import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.service.MedicalRecordService;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        // will get created by doctor during appointment
        return null;
    }

    @Override
    public MedicalRecord getMedicalRecordById(Long id) {
        return null;
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        return null;
    }

    @Override
    public void deleteMedicalRecord(Long id) {
        // TODO document why this method is empty
    }
}
