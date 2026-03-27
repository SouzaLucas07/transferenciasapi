package com.souzadev.transferenciasapi.service.externo;

import com.souzadev.transferenciasapi.infrastructure.client.AutorizacaoFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorizacaoExternaService {

    private final AutorizacaoFeignClient client;

    public boolean validarTransferencia() {
        return true;

    }
}