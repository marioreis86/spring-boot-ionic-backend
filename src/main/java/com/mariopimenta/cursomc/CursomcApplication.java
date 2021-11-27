package com.mariopimenta.cursomc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mariopimenta.cursomc.domain.Categoria;
import com.mariopimenta.cursomc.domain.Cidade;
import com.mariopimenta.cursomc.domain.Estado;
import com.mariopimenta.cursomc.domain.Produto;
import com.mariopimenta.cursomc.repositories.CategoriaRepository;
import com.mariopimenta.cursomc.repositories.CidadeRepository;
import com.mariopimenta.cursomc.repositories.EstadoRepository;
import com.mariopimenta.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired private CategoriaRepository categoriaRepository;
	@Autowired private ProdutoRepository produtoRepository;
	@Autowired private CidadeRepository cidadeRepository;
	@Autowired private EstadoRepository estadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(List.of(p1, p2, p3));
		cat2.getProdutos().addAll(List.of(p2));
		
		p1.getCategorias().addAll(List.of(cat1));
		p2.getCategorias().addAll(List.of(cat1, cat2));
		p3.getCategorias().addAll(List.of(cat1));
		
		categoriaRepository.saveAll(List.of(cat1, cat2));
		produtoRepository.saveAll(List.of(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(List.of(c1));
		est2.getCidades().addAll(List.of(c2, c3));
		
		estadoRepository.saveAll(List.of(est1, est2));
		cidadeRepository.saveAll(List.of(c1, c2, c3));
		
	}

}
