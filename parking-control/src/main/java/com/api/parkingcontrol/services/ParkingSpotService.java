package com.api.parkingcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.repository.ParkingSportRepository;

@Service
public class ParkingSpotService {
    
    @Autowired
    ParkingSportRepository parkingSportRepository;

    public ParkingSpotService(ParkingSportRepository parkingSportRepository) {
        this.parkingSportRepository = parkingSportRepository;
    }
}
