package com.miu.se.Eclincian.service.implmentation;

import com.miu.se.Eclincian.entity.Bill;
import com.miu.se.Eclincian.service.BillService;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {
    @Override
    public Bill createBill(Bill bill) {
        //will get created when a patient visit clinic(or have an appointment with a doctor)
        return null;
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
