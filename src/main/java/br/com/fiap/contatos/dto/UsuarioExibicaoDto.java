package br.com.fiap.contatos.dto;

import br.com.fiap.contatos.domain.Usuario;
import br.com.fiap.contatos.domain.UsuarioRole;

public record UsuarioExibicaoDto(
        Long id,
        String nome,
        String email,
        UsuarioRole role
) {
    public UsuarioExibicaoDto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
