package com.miu.se.Eclincian.repository;

import com.miu.se.Eclincian.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Optional<Appointment> getAppointmentByAppointmentDateAndAppointmentTime(LocalDate date, LocalTime time);

    @Query("select app from Appointment  app where app.patient.id=:patientId")
    List<Appointment> getAllAppointmentsForCurrentPatient(@Param("patientId") Long patientId);

}
