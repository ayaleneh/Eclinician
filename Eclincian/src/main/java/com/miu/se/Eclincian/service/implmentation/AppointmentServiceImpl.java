package com.miu.se.Eclincian.service.implmentation;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.repository.AppointmentRepository;
import com.miu.se.Eclincian.service.AppointmentService;
import com.miu.se.Eclincian.service.PatientService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
    }


    @Override
    public Appointment createAppointment(Long patientId, Appointment appointment) {
        //the relationship b/n appointment and user(both doctor and patient) are oneToMany,
        // so I have to make sure that they create many appointment in d/t
        // times(we can't have more than one appointment in one specific time)
        Optional<Appointment> appointment1 = appointmentRepository.getAppointmentByAppointmentDateAndAppointmentTime(appointment.getAppointmentDate(), appointment.getAppointmentTime());
        if (appointment1.isPresent()) {
            log.info("Appointment already exist");
            return null;
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.orElse(null);
    }

    @Override
    public Appointment updateAppointment(Long patientId, Long appointmentId, Appointment appointment) {
        if (appointmentRepository.existsById(appointmentId)) { //Check if the appointment exists
            Optional<Appointment> existingAppointment = appointmentRepository.findById(appointmentId);
            if (existingAppointment.isPresent()) {
                Appointment existingAppointmentEntity = existingAppointment.get();
                // Additional check to verify if the appointment belongs to the correct patient.
                if (existingAppointmentEntity.getPatient().getId().equals(patientId)) {
                    BeanUtils.copyProperties(appointment, existingAppointmentEntity, "id");// copy properties from new to an existing object, excluding id
                    appointmentRepository.save(existingAppointmentEntity);
                    return existingAppointmentEntity;
                }
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteAppointment(Long patientId,Long appointmentId) {
        try {
            if (appointmentRepository.existsById(appointmentId)) {
                Optional<Appointment> appointmentToBeDeleted = appointmentRepository.findById(appointmentId);
                if (appointmentToBeDeleted.isPresent()) {
                    Appointment appointmentToBeDeletedEntity = appointmentToBeDeleted.get();
                    if (appointmentToBeDeletedEntity.getPatient().getId().equals(patientId)) {
                        appointmentRepository.deleteById(appointmentId);
                    } else {
                        log.error("Appointment doesn't belong to the patient");
                    }
                } else {
                    log.error("Appointment to be deleted couldn't be found");
                }
            } else {
                log.error("Appointment with given id does not exist");
            }
        } catch (Exception e) {
            log.error("Error deleting appointment: ", e);
            throw new RuntimeException("Error deleting appointment", e);
        }
    }


}
