package com.miu.se.Eclincian.service.implmentation;

import com.miu.se.Eclincian.entity.*;
import com.miu.se.Eclincian.entity.dto.mapper.AppointmentMapper;
import com.miu.se.Eclincian.entity.dto.mapper.DoctorMapper;
import com.miu.se.Eclincian.entity.dto.mapper.MedicalRecordMapper;
import com.miu.se.Eclincian.entity.dto.response.AppointmentResponseDTO;
import com.miu.se.Eclincian.entity.dto.response.DoctorResponseDTO;
import com.miu.se.Eclincian.entity.dto.response.MedicalRecordResponseDTO;
import com.miu.se.Eclincian.helper.GetUser;
import com.miu.se.Eclincian.repository.AppointmentRepository;
import com.miu.se.Eclincian.repository.DoctorRepository;
import com.miu.se.Eclincian.repository.MedicalRecordRepository;
import com.miu.se.Eclincian.repository.PatientRepository;
import com.miu.se.Eclincian.service.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private  final DoctorMapper doctorMapper;
    private final MedicalRecordMapper medicalRecordMapper;

    private final MedicalRecordRepository medicalRecordRepository;

    private final GetUser getUser;
    private final DoctorRepository doctorRepository;


    public PatientServiceImpl(PatientRepository patientRepository,
                              AppointmentRepository appointmentRepository,
                              AppointmentMapper appointmentMapper,
                              DoctorMapper doctorMapper,
                              MedicalRecordMapper medicalRecordMapper,
                              MedicalRecordRepository medicalRecordRepository,
                              GetUser getUser,
                              DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.doctorMapper = doctorMapper;
        this.medicalRecordMapper = medicalRecordMapper;
        this.medicalRecordRepository = medicalRecordRepository;
        this.getUser = getUser;
        this.doctorRepository = doctorRepository;
    }


    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {

        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient updatePatient(Long patientId, Patient patient) {

        //  Long patientId = getUser.getUser().getId();

        Optional<Patient> existingPatient = patientRepository.findById(patientId);
        if (existingPatient.isPresent()) {
            Patient existingPatientEntity = existingPatient.get();
            BeanUtils.copyProperties(patient, existingPatientEntity, "id");
            patientRepository.save(existingPatientEntity);
            return existingPatientEntity;
        }
        return null;
    }

    @Override
    public void deletePatient(Long patientId) {
        // Long patientId = getUser.getUser().getId();
        patientRepository.deleteById(patientId);
    }

    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {
        Long patientId = getUser.getUser().getId();

        List<Appointment> appointments = appointmentRepository.getAllAppointmentsForCurrentPatient(patientId);
        return appointments.stream()
                .map(appointmentMapper::convertToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<AppointmentResponseDTO> getAllUpComingAppointments() {
        Long patientId = getUser.getUser().getId();
        List<Appointment> appointments = appointmentRepository.getAllUpComingAppointmentForCurrentPatient(patientId);
        return appointments.stream()
                .map(appointmentMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordResponseDTO getMedicalRecord() {
        Long patientId = getUser.getUser().getId();
        MedicalRecord medicalRecord= patientRepository.getAllMedicalRecordByPatientId(patientId);
        return medicalRecordMapper.convertToDTO(medicalRecord);
    }

    @Override
    public List<Bill> getAllBillsBelongsToCurrentPatient() {
        Long patientId = getUser.getUser().getId();
        return patientRepository.getAllBillsBelongToCurrentPatient(patientId);
    }

    @Override
    public List<DoctorResponseDTO> getAllDoctors() {
        List<Doctor> doctors=doctorRepository.findAll();
        return doctors.stream()
                .map(doctorMapper::convertToDTO)
                .toList();
    }

}
