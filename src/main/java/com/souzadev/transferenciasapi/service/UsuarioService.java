package com.souzadev.transferenciasapi.service;

import com.souzadev.transferenciasapi.domain.entity.Usuario;
import com.souzadev.transferenciasapi.domain.exception.UsuarioNaoEncontradoException;
import com.souzadev.transferenciasapi.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario buscarUsuario(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com ID: " + id));
    }
}