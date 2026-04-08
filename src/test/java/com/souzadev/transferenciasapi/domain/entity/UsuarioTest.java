package com.souzadev.transferenciasapi.domain.entity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void deveriaCriarUsuarioComDadosValidos() {
        // GIVEN - Preparar os dados
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNomeCompleto("João Silva");
        usuario.setEmail("joao@email.com");
        usuario.setTipoUsuario(TipoUsuario.COMUM);

        Saldo saldo = new Saldo();
        saldo.setValor(new BigDecimal("1000.00"));
        usuario.setSaldo(saldo);

        // THEN - Verificar se os dados foram salvos
        assertEquals(1L, usuario.getId());
        assertEquals("João Silva", usuario.getNomeCompleto());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals(TipoUsuario.COMUM, usuario.getTipoUsuario());
        assertEquals(0, new BigDecimal("1000.00").compareTo(usuario.getSaldo().getValor()));
    }

    @Test
    void deveriaPermitirAlterarSaldo() {
        // GIVEN
        Usuario usuario = new Usuario();
        Saldo saldo = new Saldo();
        saldo.setValor(new BigDecimal("500.00"));
        usuario.setSaldo(saldo);

        // WHEN - Executar a ação
        BigDecimal novoSaldo = usuario.getSaldo().getValor().add(new BigDecimal("100.00"));
        usuario.getSaldo().setValor(novoSaldo);

        // THEN
        assertEquals(0, new BigDecimal("600.00").compareTo(usuario.getSaldo().getValor()));
    }
}