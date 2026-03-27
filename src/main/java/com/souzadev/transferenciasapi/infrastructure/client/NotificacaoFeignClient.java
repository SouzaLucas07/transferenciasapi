package com.souzadev.transferenciasapi.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "notificacao",
        url = "https://util.devi.tools/api/v1"
)
public interface NotificacaoFeignClient {

    @PostMapping("/notify")
    void enviarNotificacao();
}