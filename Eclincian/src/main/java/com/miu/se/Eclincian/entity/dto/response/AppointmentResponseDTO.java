package com.miu.se.Eclincian.entity.dto.response;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentResponseDTO {
    private Long id;
    private LocalTime appointmentTime;
    private LocalDate appointmentDate;
    private String appointmentLocation;
    private String doctorName;
}
