package io.github.lafsdev.localizacao.service;

import io.github.lafsdev.localizacao.domain.entity.Cidade;
import io.github.lafsdev.localizacao.domain.repository.CidadeRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void listarCidadesPorNome() {
        repository.findByNomeLike("Porto%", Sort.by("habitantes"));
    }

    @Transactional
    public void listarCidadesPorNomePaginada() {
        Pageable pageable = PageRequest.of(0, 10);
        repository.findByNomeLike("Porto%", pageable).forEach(System.out::println);
    }


    @Transactional
    public void listarCidadesPorHabitantes() {
        repository.findByHabitantes(7770000L).forEach(System.out::println);
    }

    @Transactional
    public void listarCidadesPorHabitantesMenorQue() {
        repository.findByHabitantesLessThan(7770000L).forEach(System.out::println);
    }


    @Transactional
    public void salvarCidade() {
        var cidade = new Cidade(1L, "São Paulo", 12369398L);
        repository.save(cidade);
    }

    @Transactional
    public void listarCidades() {
        repository.findAll().forEach(System.out::println);
    }

    @Transactional
    public List<Cidade> filtroDinamico(Cidade cidade) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();
        Example<Cidade> example = Example.of(cidade, matcher);
        return repository.findAll(example);
    }
}
