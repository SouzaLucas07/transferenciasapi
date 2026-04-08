// TransferenciaControllerTest.java - VERSÃO CORRIGIDA
package com.souzadev.transferenciasapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.souzadev.transferenciasapi.controller.dto.TransferenciaRequest;
import com.souzadev.transferenciasapi.service.TransferenciaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransferenciaController.class)
class TransferenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransferenciaService transferenciaService;

    @Test
    void deveriaRealizarTransferenciaComSucesso() throws Exception {
        TransferenciaRequest request = new TransferenciaRequest(new BigDecimal("100.00"), 1L, 2L);
        doNothing().when(transferenciaService).transferir(any());

        mockMvc.perform(post("/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted());
    }

    @Test
    void deveriaRetornarBadRequestQuandoValorNegativo() throws Exception {
        TransferenciaRequest request = new TransferenciaRequest(new BigDecimal("-100.00"), 1L, 2L);

        mockMvc.perform(post("/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());  // ← Agora funciona!
    }

    @Test
    void deveriaRetornarBadRequestQuandoValorZero() throws Exception {
        TransferenciaRequest request = new TransferenciaRequest(BigDecimal.ZERO, 1L, 2L);

        mockMvc.perform(post("/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveriaRetornarBadRequestQuandoPagadorIdNulo() throws Exception {
        TransferenciaRequest request = new TransferenciaRequest(new BigDecimal("100.00"), null, 2L);

        mockMvc.perform(post("/transferencias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}