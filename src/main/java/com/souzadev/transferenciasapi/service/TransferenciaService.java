package com.souzadev.transferenciasapi.service;

import com.souzadev.transferenciasapi.controller.dto.TransferenciaRequest;
import com.souzadev.transferenciasapi.domain.entity.Transferencia;
import com.souzadev.transferenciasapi.domain.entity.Usuario;
import com.souzadev.transferenciasapi.domain.entity.TipoUsuario;
import com.souzadev.transferenciasapi.domain.exception.RequisicaoInvalidaException;
import com.souzadev.transferenciasapi.domain.repository.TransferenciaRepository;
import com.souzadev.transferenciasapi.service.externo.AutorizacaoExternaService;
import com.souzadev.transferenciasapi.service.externo.NotificacaoExternaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferenciaService {

    private final UsuarioService usuarioService;
    private final AutorizacaoExternaService autorizacaoService;
    private final SaldoService saldoService;
    private final TransferenciaRepository repository;
    private final NotificacaoExternaService notificacaoService;

    @Transactional
    public void transferir(TransferenciaRequest request) {

        // Busca os usuarios
        Usuario pagador = usuarioService.buscarUsuario(request.pagadorId());
        Usuario recebedor = usuarioService.buscarUsuario(request.recebedorId());

        // Validações
        validarPagadorLojista(pagador);
        validarSaldo(pagador, request.valor());

        // Validação externa
        validarAutorizacao();

        // Atualiza saldo do pagador
        BigDecimal novoSaldoPagador = pagador.getSaldo().getValor().subtract(request.valor());
        pagador.getSaldo().setValor(novoSaldoPagador);
        saldoService.salvar(pagador.getSaldo());

        // Atualiza saldo do recebedor
        BigDecimal novoSaldoRecebedor = recebedor.getSaldo().getValor().add(request.valor());
        recebedor.getSaldo().setValor(novoSaldoRecebedor);
        saldoService.salvar(recebedor.getSaldo());

        // Registra a transferência
        Transferencia transferencia = Transferencia.builder()
                .valor(request.valor())
                .pagador(pagador)
                .recebedor(recebedor)
                .build();

        repository.save(transferencia);

        // Envia notificação (não bloqueia a transação)
        enviarNotificacao();
    }

    private void validarPagadorLojista(Usuario usuario) {
        if (usuario.getTipoUsuario() == TipoUsuario.LOJISTA) {
            throw new RequisicaoInvalidaException("Lojistas não podem realizar transferências, apenas receber.");
        }
    }

    private void validarSaldo(Usuario usuario, BigDecimal valor) {
        if (usuario.getSaldo().getValor().compareTo(valor) < 0) {
            throw new RequisicaoInvalidaException("Saldo insuficiente para realizar a transferência.");
        }
    }

    private void validarAutorizacao() {
        try {
            boolean autorizada = autorizacaoService.validarTransferencia();
            if (!autorizada) {
                throw new RequisicaoInvalidaException("Transferência não autorizada pelo serviço externo.");
            }
        } catch (Exception e) {
            throw new RequisicaoInvalidaException("Serviço de autorização indisponível no momento.");
        }
    }

    private void enviarNotificacao() {
        try {
            notificacaoService.enviarNotificacao();
        } catch (Exception e) {
            System.out.println("Aviso: Falha ao enviar notificação, mas a transação foi concluída.");
        }
    }
}