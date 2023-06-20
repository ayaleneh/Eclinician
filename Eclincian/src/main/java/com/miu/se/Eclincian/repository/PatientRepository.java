package com.miu.se.Eclincian.repository;

import com.miu.se.Eclincian.entity.Bill;
import com.miu.se.Eclincian.entity.MedicalRecord;
import com.miu.se.Eclincian.entity.Patient;
import com.miu.se.Eclincian.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Query("select mr from Patient p join p.medicalRecord mr where p.id= :patientId")
    MedicalRecord getAllMedicalRecordByPatientId(Long patientId);

    @Query("select b from Patient p join p.bills b where p.id= :patientId")

    public List<Bill> getAllBillsBelongToCurrentPatient(Long patientId);
    Patient findByUser(User user);
}
