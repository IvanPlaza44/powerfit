package com.uade.tpo.service;

import com.uade.tpo.controllers.purchase.PurchaseRequest;
import com.uade.tpo.entity.Purchase;

public interface PurchaseService {
    Purchase performPurchase(PurchaseRequest request);
}