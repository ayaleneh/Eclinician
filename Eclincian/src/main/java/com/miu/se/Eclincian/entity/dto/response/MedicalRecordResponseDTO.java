package com.miu.se.Eclincian.entity.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalRecordResponseDTO {
    private Long id;
    private String patientName;
    private LocalDate date;
    private String diagnosedCondition;
    private String treatmentPlan;
    private String doctorNotes;
    private String doctorName;

}
