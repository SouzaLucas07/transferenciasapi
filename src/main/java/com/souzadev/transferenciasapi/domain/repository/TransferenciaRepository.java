package com.souzadev.transferenciasapi.domain.repository;

import com.souzadev.transferenciasapi.domain.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
}