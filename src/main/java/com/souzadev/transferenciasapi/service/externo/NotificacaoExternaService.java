package com.souzadev.transferenciasapi.service.externo;

import com.souzadev.transferenciasapi.infrastructure.client.NotificacaoFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoExternaService {

    private final NotificacaoFeignClient client;

    public void enviarNotificacao() {
        client.enviarNotificacao();
    }
}