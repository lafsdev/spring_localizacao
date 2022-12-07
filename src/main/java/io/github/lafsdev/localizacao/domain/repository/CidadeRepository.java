package io.github.lafsdev.localizacao.domain.repository;

import io.github.lafsdev.localizacao.domain.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {


    //Busca pelo nome exato
    List<Cidade> findByNome(String nome);

    //Começando por aquele pedaço
    List<Cidade> findByNomeStartingWith(String nome);

    //Terminando por aquele pedaço
    List<Cidade> findByNomeEndingWith(String nome);

    //Contendo aquele pedaço
    List<Cidade> findByNomeContaining(String nome);

    // Busca pelo nome like ordenação
    @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
    List<Cidade> findByNomeLike(String nome, Sort sort);

    @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
    Page<Cidade> findByNomeLike(String nome, Pageable page);

    List<Cidade> findByHabitantes(Long habitantes);

    List<Cidade> findByHabitantesLessThan(Long habitantes);

    List<Cidade> findByHabitantesLessThanEqual(Long habitantes);

    List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);

    List<Cidade> findByHabitantesGreaterThan(Long habitantes);


}
