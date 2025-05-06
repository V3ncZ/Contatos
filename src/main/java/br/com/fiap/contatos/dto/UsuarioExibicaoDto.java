package br.com.fiap.contatos.dto;

import br.com.fiap.contatos.domain.Usuario;

public record UsuarioExibicaoDto(
        Long id,
        String nome,
        String email
) {
    public UsuarioExibicaoDto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());
    }
}
