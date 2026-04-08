package com.souzadev.transferenciasapi.service;

import com.souzadev.transferenciasapi.controller.dto.TransferenciaRequest;
import com.souzadev.transferenciasapi.domain.entity.*;
import com.souzadev.transferenciasapi.domain.exception.RequisicaoInvalidaException;
import com.souzadev.transferenciasapi.domain.repository.TransferenciaRepository;
import com.souzadev.transferenciasapi.service.externo.AutorizacaoExternaService;
import com.souzadev.transferenciasapi.service.externo.NotificacaoExternaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do Serviço de Transferência")
class TransferenciaServiceTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private AutorizacaoExternaService autorizacaoService;

    @Mock
    private SaldoService saldoService;

    @Mock
    private TransferenciaRepository repository;

    @Mock
    private NotificacaoExternaService notificacaoService;

    @InjectMocks
    private TransferenciaService transferenciaService;

    private Usuario pagador;
    private Usuario recebedor;
    private TransferenciaRequest request;

    @BeforeEach
    void setUp() {
        pagador = new Usuario();
        pagador.setId(1L);
        pagador.setTipoUsuario(TipoUsuario.COMUM);

        Saldo saldoPagador = new Saldo();
        saldoPagador.setValor(new BigDecimal("1000.00"));
        pagador.setSaldo(saldoPagador);

        recebedor = new Usuario();
        recebedor.setId(2L);

        Saldo saldoRecebedor = new Saldo();
        saldoRecebedor.setValor(new BigDecimal("500.00"));
        recebedor.setSaldo(saldoRecebedor);

        request = new TransferenciaRequest(new BigDecimal("100.00"), 1L, 2L);
    }

    @Test
    @DisplayName("Deveria realizar transferência com sucesso")
    void deveriaRealizarTransferenciaComSucesso() {
        when(usuarioService.buscarUsuario(1L)).thenReturn(pagador);
        when(usuarioService.buscarUsuario(2L)).thenReturn(recebedor);
        when(autorizacaoService.validarTransferencia()).thenReturn(true);

        assertDoesNotThrow(() -> transferenciaService.transferir(request));

        verify(saldoService, times(2)).salvar(any(Saldo.class));
        verify(repository, times(1)).save(any(Transferencia.class));
    }

    @Test
    @DisplayName("Deveria lançar exceção quando pagador é lojista")
    void deveriaLancarExcecaoQuandoPagadorForLojista() {
        pagador.setTipoUsuario(TipoUsuario.LOJISTA);
        when(usuarioService.buscarUsuario(1L)).thenReturn(pagador);

        RequisicaoInvalidaException exception = assertThrows(
                RequisicaoInvalidaException.class,
                () -> transferenciaService.transferir(request)
        );

        assertEquals("Lojistas não podem realizar transferências, apenas receber.",
                exception.getMessage());
    }

    @Test
    @DisplayName("Deveria lançar exceção quando saldo é insuficiente")
    void deveriaLancarExcecaoQuandoSaldoInsuficiente() {
        TransferenciaRequest requestGrande = new TransferenciaRequest(new BigDecimal("2000.00"), 1L, 2L);
        when(usuarioService.buscarUsuario(1L)).thenReturn(pagador);

        RequisicaoInvalidaException exception = assertThrows(
                RequisicaoInvalidaException.class,
                () -> transferenciaService.transferir(requestGrande)
        );

        assertEquals("Saldo insuficiente para realizar a transferência.",
                exception.getMessage());
    }

    @Test
    @DisplayName("Deveria lançar exceção quando autorização é negada")
    void deveriaLancarExcecaoQuandoAutorizacaoRecusa() {
        when(usuarioService.buscarUsuario(1L)).thenReturn(pagador);
        when(usuarioService.buscarUsuario(2L)).thenReturn(recebedor);
        when(autorizacaoService.validarTransferencia()).thenReturn(false);

        RequisicaoInvalidaException exception = assertThrows(
                RequisicaoInvalidaException.class,
                () -> transferenciaService.transferir(request)
        );

        // CORRIGIDO: mensagem correta
        assertEquals("Transferência não autorizada pelo serviço externo.",
                exception.getMessage());
    }
}