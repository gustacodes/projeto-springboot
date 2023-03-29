package com.api.parkingcontrol.controller;

import com.api.parkingcontrol.dtos.VagasDto;
import com.api.parkingcontrol.model.Vagas;
import com.api.parkingcontrol.services.VagasServices;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class VagasController {

    final VagasServices vagasService;

    public VagasController(VagasServices vagasService) {
        this.vagasService = vagasService;
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid VagasDto vagasDto){
        if(vagasService.existsByPlacaDoVeiculo(vagasDto.getPlacaDoVeiculo())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Esta placa já está em uso no sistema.");
        }

        if(vagasService.existsByVaga(vagasDto.getVaga())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Esta vaga está em uso.");
        }

        var vagas = new Vagas();
        BeanUtils.copyProperties(vagasDto, vagas);
        vagas.setDataEntrada(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(vagasService.save(vagas));
    }

    @GetMapping
    public ResponseEntity<Page<Vagas>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(vagasService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id ) {
        Optional<Vagas> vagaOptional = vagasService.findById(id);
        
            if(!vagaOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
            }

            return ResponseEntity.status(HttpStatus.OK).body(vagaOptional.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteVaga(@ PathVariable("id") UUID id) {
        Optional<Vagas> vagaOptional = vagasService.findById(id);

            if(!vagaOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada.");
            }

            vagasService.delete(vagaOptional.get());

            return ResponseEntity.status(HttpStatus.OK).body("Vaga deletada com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid VagasDto parkingSpotDto){
        
        Optional<Vagas> parkingSpotModelOptional = vagasService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }
        var parkingSpotModel = new Vagas();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setDataEntrada(parkingSpotModelOptional.get().getDataEntrada());
        return ResponseEntity.status(HttpStatus.OK).body(vagasService.save(parkingSpotModel));
    }
    
}
