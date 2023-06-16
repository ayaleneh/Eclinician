package com.miu.se.Eclincian.repository;

import com.miu.se.Eclincian.entity.Doctor;
import com.miu.se.Eclincian.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query("select p from Doctor d join d.patients p where d.id= :doctorId")
    public List<Patient> getAllPatients(Long doctorId);
    boolean existsByIdAndPatientsContaining(Long doctorId, Patient patient);


}
