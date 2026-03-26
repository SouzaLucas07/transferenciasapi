package com.souzadev.transferenciasapi.domain.repository;

import com.souzadev.transferenciasapi.domain.entity.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaldoRepository extends JpaRepository<Saldo, Long> {
}