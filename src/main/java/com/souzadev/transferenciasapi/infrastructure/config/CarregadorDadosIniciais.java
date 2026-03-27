package com.souzadev.transferenciasapi.infrastructure.config;

import com.souzadev.transferenciasapi.domain.entity.Saldo;
import com.souzadev.transferenciasapi.domain.entity.TipoUsuario;
import com.souzadev.transferenciasapi.domain.entity.Usuario;
import com.souzadev.transferenciasapi.domain.repository.SaldoRepository;
import com.souzadev.transferenciasapi.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CarregadorDadosIniciais {

    private final UsuarioRepository usuarioRepository;
    private final SaldoRepository saldoRepository;

    @Bean
    public CommandLineRunner carregarDados() {
        return args -> {
            if (usuarioRepository.count() == 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                // Usuários COMUM
                Usuario usuario1 = new Usuario();
                usuario1.setNomeCompleto("Lucas Souza");
                usuario1.setEmail("souza.lucasp10@gmail.com");
                usuario1.setCpfCnpj("47680065829");
                usuario1.setSenha(encoder.encode("07122000"));
                usuario1.setTipoUsuario(TipoUsuario.COMUM);

                Usuario usuario2 = new Usuario();
                usuario2.setNomeCompleto("Milena Souza");
                usuario2.setEmail("vieira.milena07@gmail.com");
                usuario2.setCpfCnpj("012345678901");
                usuario2.setSenha(encoder.encode("07122000"));
                usuario2.setTipoUsuario(TipoUsuario.COMUM);

                Usuario usuario3 = new Usuario();
                usuario3.setNomeCompleto("João Cliente");
                usuario3.setEmail("joao.cliente@gmail.com");
                usuario3.setCpfCnpj("98765432100");
                usuario3.setSenha(encoder.encode("07122000"));
                usuario3.setTipoUsuario(TipoUsuario.COMUM);

                // Usuários LOJISTA
                Usuario lojista1 = new Usuario();
                lojista1.setNomeCompleto("Alexandro Caldeira");
                lojista1.setEmail("caldeira.alexandro@gmail.com");
                lojista1.setCpfCnpj("00000000000");
                lojista1.setSenha(encoder.encode("07122000"));
                lojista1.setTipoUsuario(TipoUsuario.LOJISTA);

                Usuario lojista2 = new Usuario();
                lojista2.setNomeCompleto("Maria Lojista");
                lojista2.setEmail("maria.loja@gmail.com");
                lojista2.setCpfCnpj("12345678000199");
                lojista2.setSenha(encoder.encode("07122000"));
                lojista2.setTipoUsuario(TipoUsuario.LOJISTA);

                // Salvar usuários
                List<Usuario> usuarios = usuarioRepository.saveAll(List.of(usuario1, usuario2, usuario3, lojista1, lojista2));

                // Criar saldos para cada usuário
                Saldo saldo1 = new Saldo(null, new BigDecimal("2500.00"), usuario1);
                Saldo saldo2 = new Saldo(null, new BigDecimal("1000.00"), usuario2);
                Saldo saldo3 = new Saldo(null, new BigDecimal("500.00"), usuario3);
                Saldo saldo4 = new Saldo(null, new BigDecimal("10000.00"), lojista1);
                Saldo saldo5 = new Saldo(null, new BigDecimal("15000.00"), lojista2);

                saldoRepository.saveAll(List.of(saldo1, saldo2, saldo3, saldo4, saldo5));

                System.out.println("✅ Dados iniciais carregados com sucesso!");
                System.out.println("📊 Usuários: " + usuarios.size());
                System.out.println("💰 Saldos: 5 registros criados");
            } else {
                System.out.println("ℹ️ Banco já possui dados. Pulando carga inicial.");
            }
        };
    }
}