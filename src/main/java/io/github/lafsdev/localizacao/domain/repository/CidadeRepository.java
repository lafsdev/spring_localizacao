package io.github.lafsdev.localizacao.domain.repository;

import io.github.lafsdev.localizacao.domain.entity.Cidade;
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

    // Busca pelo nome like
    @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
    List<Cidade> findByNomeLike(String nome);

    List<Cidade> findByHabitantes(Long habitantes);

}
