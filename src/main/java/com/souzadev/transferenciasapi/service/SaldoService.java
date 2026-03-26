package com.souzadev.transferenciasapi.service;

import com.souzadev.transferenciasapi.domain.entity.Saldo;
import com.souzadev.transferenciasapi.domain.repository.SaldoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaldoService {

    private final SaldoRepository repository;

    public void salvar(Saldo saldo) {
        repository.save(saldo);
    }
}