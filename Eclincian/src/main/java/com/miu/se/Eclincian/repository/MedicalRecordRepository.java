package com.miu.se.Eclincian.repository;

import com.miu.se.Eclincian.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {

 @Query("select mr from MedicalRecord mr where mr.patient.id= :patientId AND mr.doctor.id= :doctorId")
 List<MedicalRecord> getMedicalRecordsByPatientIdAndDoctorId(Long patientId, Long doctorId);
}
