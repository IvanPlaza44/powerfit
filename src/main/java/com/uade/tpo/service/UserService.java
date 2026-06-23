package com.uade.tpo.service;

public interface UserService {

    void becomeSeller(String username);
    Integer getPoints(Long userId);

}