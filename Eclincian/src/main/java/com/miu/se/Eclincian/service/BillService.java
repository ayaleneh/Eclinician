package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.Bill;

public interface BillService {
    public Bill createBill(Long patientId,Bill bill);//Admin can see patient list and select patient to add
    // a bill if there is a related passed appointment with that specific patient

    public Bill getBillById(Long id);

    public Bill updateBill(Bill bill);

    public void deleteBill(Long id);
}
