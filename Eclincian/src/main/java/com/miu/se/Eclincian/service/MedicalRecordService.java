package com.miu.se.Eclincian.service;
import com.miu.se.Eclincian.entity.MedicalRecord;

public interface MedicalRecordService {
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

    public MedicalRecord getMedicalRecordById(Long id);

    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

    public void deleteMedicalRecord(Long id);
}
