package com.souzadev.transferenciasapi.controller;

import com.souzadev.transferenciasapi.controller.dto.TransferenciaRequest;
import com.souzadev.transferenciasapi.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;  // ← IMPORTANTE: importar este
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transferencias")
@Tag(name = "Transferências", description = "Endpoints para gerenciamento de transferências")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @PostMapping
    @Operation(summary = "Realizar transferência")
    public ResponseEntity<Void> realizarTransferencia(@Valid @RequestBody TransferenciaRequest request) {  // ← Adicionar @Valid
        transferenciaService.transferir(request);
        return ResponseEntity.accepted().build();
    }
}