package io.github.lafsdev.localizacao.domain.repository;

import io.github.lafsdev.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
