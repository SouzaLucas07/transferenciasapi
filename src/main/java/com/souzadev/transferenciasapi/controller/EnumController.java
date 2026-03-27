package com.souzadev.transferenciasapi.controller;

import com.souzadev.transferenciasapi.domain.entity.TipoUsuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enums")
@Tag(name = "Enums", description = "Endpoints para consulta de enums")
public class EnumController {

    @GetMapping("/tipo-usuario")
    @Operation(summary = "Lista os tipos de usuário")
    public TipoUsuario[] getTipoUsuario() {
        return TipoUsuario.values();
    }
}