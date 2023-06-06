package com.miu.se.Eclincian.repository;

import com.miu.se.Eclincian.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

}
