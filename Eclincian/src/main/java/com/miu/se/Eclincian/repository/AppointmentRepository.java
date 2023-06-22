package com.miu.se.Eclincian.repository;

import com.miu.se.Eclincian.entity.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("select app from Appointment  app where app.patient.id= :patientId order by app.appointmentDate DESC , app.appointmentTime DESC ")
    List<Appointment> getAllAppointmentsForCurrentPatient(@Param("patientId") Long patientId);

    @Query("SELECT app FROM Appointment app WHERE app.appointmentDate >= CURRENT_DATE AND app.patient.id= :patientId order by app.appointmentDate ASC , app.appointmentTime ASC")
    List<Appointment> getAllUpComingAppointmentForCurrentPatient(Long patientId);


    @Modifying
    @Transactional
    @Query("delete from Appointment app where app.id= :appointmentId AND app.patient.id= :patientId")
    void deleteAppointmentByIdAndAndPatientId(@Param("appointmentId") Long appointmentId, @Param("patientId") Long patientId);


    //Doctor
    @Query("select app from Appointment  app where app.doctor.id= :doctorId order by app.appointmentDate ASC , app.appointmentTime ASC")
    public List<Appointment> getAllAppointmentsByDoctorId(Long doctorId);

    @Query("SELECT app FROM Appointment app WHERE app.appointmentDate >= CURRENT_DATE AND app.doctor.id= :doctorId order by app.appointmentDate ASC , app.appointmentTime ASC")
    List<Appointment> getAllUpComingAppointmentForCurrentDoctor(Long doctorId);


}
