package io.github.lafsdev.localizacao.service;

import io.github.lafsdev.localizacao.domain.entity.Cidade;
import io.github.lafsdev.localizacao.domain.repository.CidadeRepository;
import io.github.lafsdev.localizacao.domain.repository.speces.CidadeSpecs;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static io.github.lafsdev.localizacao.domain.repository.speces.CidadeSpecs.*;

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

    public void listarCidadesByNomeSpec() {

        repository.findAll(CidadeSpecs.nomeEqual("São Paulo").or(habitantesGreaterThan(1000L))).forEach(System.out::println);
    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());

        //select * from cidade where 1 = 1

        if(filtro.getId() != null){
            specs = specs.and( idEqual(filtro.getId()) );
        }

        if(StringUtils.hasText(filtro.getNome())){
            specs = specs.and(nomeLike(filtro.getNome()));
        }

        if(filtro.getHabitantes() != null){
            specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
        }

        repository.findAll(specs).forEach(System.out::println);

    }
}
