package com.api.parkingcontrol.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VagasDto {
    
    @NotBlank
    private String vaga;
    @NotBlank
    @Size(max = 8)
    private String placaDoVeiculo;
    @NotBlank
    private String cliente;
    

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String Vaga) {
        this.vaga = Vaga;
    }

    public String getPlacaDoVeiculo() {
        return placaDoVeiculo;
    }

    public void setPlacaDoVeiculo(String placaDoVeiculo) {
        this.placaDoVeiculo = placaDoVeiculo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

}
