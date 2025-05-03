package br.com.fiap.contatos.service;

import br.com.fiap.contatos.domain.Contato;
import br.com.fiap.contatos.dto.ContatoCadastroDto;
import br.com.fiap.contatos.dto.ContatoExibicaoDto;
import br.com.fiap.contatos.exception.UsuarioNaoEncontradoException;
import br.com.fiap.contatos.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public ContatoExibicaoDto gravar(ContatoCadastroDto contatoCadastroDto) {
        Contato contato = new Contato();
        BeanUtils.copyProperties(contatoCadastroDto, contato); // Copia os dados do DTO para o objeto Contato
        return new ContatoExibicaoDto(contatoRepository.save(contato));
    }

    public ContatoExibicaoDto buscarPorId(Long id) {

        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if (contatoOptional.isPresent()) {
            return new ContatoExibicaoDto(contatoOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Contato não encontrado");
        }

    }

    public Page<ContatoExibicaoDto> listarTodosOsContatos(Pageable paginacao) {
        return contatoRepository.findAll(paginacao).map(ContatoExibicaoDto::new);
    }

    public void deletar(Long id) {

        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if (contatoOptional.isPresent()) {
            contatoRepository.delete(contatoOptional.get());
            System.out.println("Contato deletado com sucesso");
        } else {
            throw new RuntimeException("Contato não encontrado");
        }


    }

    public List<Contato> mostrarAniversariantes(LocalDate dataInicial, LocalDate dataFinal) {
        return contatoRepository.findByDataNascimentoBetween(dataInicial, dataFinal);
    }

    public Contato atualizar(Contato contato) {

        // Instancia um contato opcional para que possamos fazer uma validação
        Optional<Contato> contatoOptional = contatoRepository.findById(contato.getId());

        // Validação
        if (contatoOptional.isPresent()) {
            return contatoRepository.save(contato);
        } else {
            throw new RuntimeException("Contato não encontrado");
        }
    }

    public Contato encontrarPorNome(String nome) {

        Optional<Contato> contatoOptional = contatoRepository.findByNome(nome);

        if (contatoOptional.isPresent()) {
            return contatoOptional.get();
        } else {
            throw new RuntimeException("Contato não encontrado");
        }

    }
}
