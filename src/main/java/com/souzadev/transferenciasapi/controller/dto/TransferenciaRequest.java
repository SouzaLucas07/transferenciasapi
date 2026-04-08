package com.souzadev.transferenciasapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "DTO para requisição de transferência")
public record TransferenciaRequest(

        @NotNull(message = "Valor é obrigatório")
        @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
        @Schema(
                description = "Valor da transferência",
                example = "100.50",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minimum = "0.01"
        )
        BigDecimal valor,

        @NotNull(message = "Pagador ID é obrigatório")
        @Schema(
                description = "ID do usuário pagador",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long pagadorId,

        @NotNull(message = "Recebedor ID é obrigatório")
        @Schema(
                description = "ID do usuário recebedor",
                example = "2",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long recebedorId
) {}