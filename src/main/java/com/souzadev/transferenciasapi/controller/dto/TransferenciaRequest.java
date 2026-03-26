package com.souzadev.transferenciasapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "DTO para requisição de transferência")
public record TransferenciaRequest(

        @Schema(
                description = "Valor da transferência",
                example = "100.50",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minimum = "0.01"
        )
        BigDecimal valor,

        @Schema(
                description = "ID do usuário pagador",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long pagadorId,

        @Schema(
                description = "ID do usuário recebedor",
                example = "2",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long recebedorId
) {}