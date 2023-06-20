package com.miu.se.Eclincian.service.implmentation;

import com.miu.se.Eclincian.Contract.AppointmentMapper;
import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.Doctor;
import com.miu.se.Eclincian.entity.Patient;
import com.miu.se.Eclincian.entity.User;
import com.miu.se.Eclincian.entity.dto.response.AppointmentResponseDTO;
import com.miu.se.Eclincian.helper.GetUser;
import com.miu.se.Eclincian.repository.AppointmentRepository;
import com.miu.se.Eclincian.repository.DoctorRepository;
import com.miu.se.Eclincian.repository.PatientRepository;
import com.miu.se.Eclincian.service.AppointmentService;
import com.miu.se.Eclincian.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final GetUser getUser;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  PatientService patientService,
                                  GetUser getUser,
                                  PatientRepository patientRepository,
                                  DoctorRepository doctorRepository,
                                  AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.getUser = getUser;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentMapper = appointmentMapper;
    }


    @Override
    public Appointment createAppointment(Appointment appointment) {

        Optional<Appointment> appointment1 = appointmentRepository.getAppointmentByAppointmentDateAndAppointmentTime(
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime());

        if (appointment1.isPresent()) {
            log.info("Appointment already exist");
            return null;
        }
        User user = getUser.getUser();
        if (user == null) {
            log.info("No authenticated user found");
            return null;
        }

        Patient patient = patientRepository.findByUser(user);

        if (patient == null) {
            log.info("No patient found for the authenticated user");
            return null;
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById(appointment.getDoctor().getId());
        if (optionalDoctor.isEmpty()) {
            log.info("Doctor not found");
        }

        Doctor doctor = optionalDoctor.get();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> getAllAppointment() {
        List<Appointment> appointments= appointmentRepository.findAll();
        return appointments.stream().map(appointmentMapper::convertToDTO).toList();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.orElse(null);
    }

    @Override
    public Appointment updateAppointment(Long appointmentId, Appointment appointment) {
        Long patientId = getUser.getUser().getId();
        if (appointmentRepository.existsById(appointmentId)) {
            Optional<Appointment> existingAppointment = appointmentRepository.findById(appointmentId);
            if (existingAppointment.isPresent()) {
                Appointment existingAppointmentEntity = existingAppointment.get();
                if (existingAppointmentEntity.getPatient().getId().equals(patientId)) {


                    Patient patientEntity = patientRepository.findById(patientId)
                            .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

                    Doctor doctorEntity = doctorRepository.findById(appointment.getDoctor().getId())
                            .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

                    BeanUtils.copyProperties(appointment, existingAppointmentEntity, "id", "patient", "doctor");


                    existingAppointmentEntity.setPatient(patientEntity);
                    existingAppointmentEntity.setDoctor(doctorEntity);

                    appointmentRepository.save(existingAppointmentEntity);
                    return existingAppointmentEntity;
                }
            }
        }
        return null;
    }


    @Override
    @Transactional
    public void deleteAppointment(Long appointmentId) {
        Long patientId = getUser.getUser().getId();
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
