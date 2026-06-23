package com.uade.tpo.controllers.user;

import lombok.Data;

@Data
public class RedeemPointsRequest {

    private Integer pointsCost;
    private String benefitName;

}
