package com.api.parkingcontrol.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.parkingcontrol.model.Vagas;

public interface VagasRepository extends JpaRepository<Vagas, UUID> {

    boolean existsByPlacaDoVeiculo(String placaDoVeiculo);
    boolean existsByVaga(String vaga);
    
}
