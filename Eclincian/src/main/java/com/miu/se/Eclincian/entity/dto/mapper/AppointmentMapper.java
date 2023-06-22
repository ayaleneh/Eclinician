package com.miu.se.Eclincian.entity.dto.mapper;

import com.miu.se.Eclincian.entity.Appointment;
import com.miu.se.Eclincian.entity.dto.response.AppointmentResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    private final ModelMapper modelMapper;

    public AppointmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AppointmentResponseDTO convertToDTO(Appointment appointment) {
        AppointmentResponseDTO appointmentDTO = modelMapper.map(appointment, AppointmentResponseDTO.class);
        appointmentDTO.setDoctorName(appointment.getDoctor().getUser().getFName());
        appointmentDTO.setPatientName(appointment.getPatient().getUser().getFName());
        return appointmentDTO;
    }
    public Appointment convertToEntity(AppointmentResponseDTO appointmentDTO){
        return modelMapper.map(appointmentDTO,Appointment.class);
    }
}






