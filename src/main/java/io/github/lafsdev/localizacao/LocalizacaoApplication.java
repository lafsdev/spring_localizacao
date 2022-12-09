package io.github.lafsdev.localizacao;

import io.github.lafsdev.localizacao.domain.entity.Cidade;
import io.github.lafsdev.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	CidadeService service;

	@Override
	public void run(String... args) throws Exception {
		Cidade cidade = new Cidade(1L, "São Paulo", 100L);
		service.listarCidadesSpecsFiltroDinamico(cidade);
	}





	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
