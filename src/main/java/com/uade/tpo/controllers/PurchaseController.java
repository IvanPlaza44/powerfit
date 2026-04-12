package com.uade.tpo.controllers;

import com.uade.tpo.entity.Purchase;
import com.uade.tpo.entity.dto.PurchaseRequest;
import com.uade.tpo.service.PurchaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public Purchase createPurchase(@RequestBody PurchaseRequest request) {
    System.out.println("REQUEST: " + request);
    System.out.println("USER ID: " + request.getUserId());
    return purchaseService.performPurchase(request);
    }
}