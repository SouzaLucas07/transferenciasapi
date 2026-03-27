package com.souzadev.transferenciasapi.infrastructure.client;

import com.souzadev.transferenciasapi.infrastructure.client.dto.RespostaAutorizacao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "autorizacao",
        url = "https://util.devi.tools/api/v2"
)
public interface AutorizacaoFeignClient {

    @GetMapping("/authorize")
    RespostaAutorizacao validarAutorizacao();
}