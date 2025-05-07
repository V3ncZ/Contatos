package br.com.fiap.contatos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @NotBlank(message = "O campo email não pode ser vazio!")
        @Email(message = "O email esta inválido!")
        String email,
        @NotBlank(message = "O campo senha não pode ser vazio!")
        @Size(min = 6, max = 12, message = "A senha deve ter entre 6 e 12 caracteres")
        String senha
) {
}
