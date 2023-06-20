package com.miu.se.Eclincian.service.implmentation;

import com.miu.se.Eclincian.entity.Bill;
import com.miu.se.Eclincian.entity.Patient;
import com.miu.se.Eclincian.repository.PatientRepository;
import com.miu.se.Eclincian.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BillServiceImpl implements BillService {
    private final PatientRepository patientRepository;

    public BillServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Bill createBill(Long patientId, Bill bill) {
        Patient patientToHaveAbillEntity = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient with id " + patientId + " not found."));

        if (patientToHaveAbillEntity.getAppointments() != null &&
                patientToHaveAbillEntity.getAppointments()
                        .stream()
                        .anyMatch(appointment -> appointment.getAppointmentDate().isBefore(LocalDate.now()))) {

            // Add new bill to existing list of bills
            List<Bill> existingBills = patientToHaveAbillEntity.getBills();
            if (existingBills == null) {
                existingBills = new ArrayList<>();
            }
            existingBills.add(bill);
            patientToHaveAbillEntity.setBills(existingBills);

            patientRepository.save(patientToHaveAbillEntity);
            return bill;
        }
        throw new RuntimeException("Patient doesn't have any upcoming appointments.");
    }


    @Override
    public Bill getBillById(Long id) {
        return null;
    }

    @Override
    public Bill updateBill(Bill bill) {
        return null;
    }

    @Override
    public void deleteBill(Long id) {

    }
}
