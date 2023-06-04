package com.miu.se.Eclincian.service;

import com.miu.se.Eclincian.entity.Bill;

public interface BillService {
    public Bill createBill(Bill bill);

    public Bill getBillById(Long id);

    public Bill updateBill(Bill bill);

    public void deleteBill(Long id);
}
