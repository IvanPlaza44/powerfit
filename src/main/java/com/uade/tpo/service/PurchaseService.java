package com.uade.tpo.service;

import com.uade.tpo.entity.Purchase;
import com.uade.tpo.entity.dto.PurchaseRequest;

public interface PurchaseService {
    Purchase performPurchase(PurchaseRequest request);
}