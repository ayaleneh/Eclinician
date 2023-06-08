package com.miu.se.Eclincian.service.implmentation;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.repository.AppointmentRepository;
import com.miu.se.Eclincian.service.AppointmentService;
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

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    @Override
    public Appointment createAppointment(Long patientId,Appointment appointment) {
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
    public Appointment updateAppointment(Long appointmentId, Appointment appointment) {
        Optional<Appointment> existingAppointment = appointmentRepository.findById(appointmentId);
        if (existingAppointment.isPresent()) {
            BeanUtils.copyProperties(appointment, existingAppointment, "id");// copy properties from new to an existing object,excluding id
        }
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
