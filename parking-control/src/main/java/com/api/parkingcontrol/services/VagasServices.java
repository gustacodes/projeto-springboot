package com.api.parkingcontrol.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.model.Vagas;
import com.api.parkingcontrol.repository.VagasRepository;

import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class VagasServices {

    final VagasRepository vagasRepository;

    public VagasServices(VagasRepository vagasRepository) {
        this.vagasRepository = vagasRepository;
    }

    @Transactional
    public Vagas save(Vagas vagas) {
        return vagasRepository.save(vagas);
    }

    public boolean existsByPlacaDoVeiculo(String placaDoVeiculo) {
        return vagasRepository.existsByPlacaDoVeiculo(placaDoVeiculo);
    }

    public boolean existsByVaga(String vaga) {
        return vagasRepository.existsByVaga(vaga);
    }

    public Page<Vagas> findAll(Pageable pageable) {
        return vagasRepository.findAll(pageable);
    }

    public Optional<Vagas> findById(UUID id) {
        return vagasRepository.findById(id);
    }

    @Transactional
    public void delete(Vagas vaga) {
        vagasRepository.delete(vaga);
    }
}