package io.github.lafsdev.localizacao;

import io.github.lafsdev.localizacao.domain.entity.Cidade;
import io.github.lafsdev.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {
		listarCidadesPorNome();
	}

	@Transactional
	void listarCidadesPorNome() {
		cidadeRepository.findByNomeEndingWith("a").forEach(System.out::println);
	}

	@Transactional
	void listarCidadesPorHabitantes() {
		cidadeRepository.findByHabitantes(7770000L).forEach(System.out::println);
	}


	@Transactional
	void salvarCidade() {
		var cidade = new Cidade(1L, "São Paulo", 12369398L);
		cidadeRepository.save(cidade);
	}

	@Transactional
	void listarCidades() {
		cidadeRepository.findAll().forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
