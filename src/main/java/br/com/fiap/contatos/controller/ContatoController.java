package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.domain.Contato;
import br.com.fiap.contatos.dto.ContatoCadastroDto;
import br.com.fiap.contatos.dto.ContatoExibicaoDto;
import br.com.fiap.contatos.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @PostMapping("/contatos")
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoExibicaoDto gravar(@RequestBody @Valid ContatoCadastroDto contatoCadastroDto) {
        return service.gravar(contatoCadastroDto);
    }

    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContatoExibicaoDto> listarTodosOsContatos(Pageable paginacao) {
        return service.listarTodosOsContatos(paginacao);
    }

    @GetMapping("/contatos/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto encontrarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.deletar(id);
    }

    @PutMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Contato atualizar(@RequestBody Contato contato) {
        return service.atualizar(contato);
    }

    @GetMapping("/contatos/{nome}")
    public Contato encontrarPorNome(@PathVariable String nome) {
        return service.encontrarPorNome(nome);
    }

    @GetMapping("/contatos/{dataInicial}/{dataFinal}")
    public List<Contato> mostrarAniversariantes(@PathVariable LocalDate dataInicial,
                                                @PathVariable LocalDate dataFinal) {
        return service.mostrarAniversariantes(dataInicial, dataFinal);
    }

}
