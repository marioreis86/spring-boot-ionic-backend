package com.mariopimenta.cursomc;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mariopimenta.cursomc.domain.Categoria;
import com.mariopimenta.cursomc.domain.Cidade;
import com.mariopimenta.cursomc.domain.Cliente;
import com.mariopimenta.cursomc.domain.Endereco;
import com.mariopimenta.cursomc.domain.Estado;
import com.mariopimenta.cursomc.domain.Pagamento;
import com.mariopimenta.cursomc.domain.PagamentoComBoleto;
import com.mariopimenta.cursomc.domain.PagamentoComCartao;
import com.mariopimenta.cursomc.domain.Pedido;
import com.mariopimenta.cursomc.domain.Produto;
import com.mariopimenta.cursomc.domain.enums.EstadoPagamento;
import com.mariopimenta.cursomc.domain.enums.TipoCliente;
import com.mariopimenta.cursomc.repositories.CategoriaRepository;
import com.mariopimenta.cursomc.repositories.CidadeRepository;
import com.mariopimenta.cursomc.repositories.ClienteRepository;
import com.mariopimenta.cursomc.repositories.EnderecoRepository;
import com.mariopimenta.cursomc.repositories.EstadoRepository;
import com.mariopimenta.cursomc.repositories.PagamentoRepository;
import com.mariopimenta.cursomc.repositories.PedidoRepository;
import com.mariopimenta.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired private CategoriaRepository categoriaRepository;
	@Autowired private ProdutoRepository produtoRepository;
	@Autowired private CidadeRepository cidadeRepository;
	@Autowired private EstadoRepository estadoRepository;
	@Autowired private ClienteRepository clienteRepository;
	@Autowired private EnderecoRepository enderecoRepository;
	@Autowired private PedidoRepository pedidoRepository;
	@Autowired private PagamentoRepository pagamentoRepository;

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
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "mario@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(List.of("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(List.of(e1, e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(List.of(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(List.of(ped1, ped2));
		
		pedidoRepository.saveAll(List.of(ped1, ped2));
		pagamentoRepository.saveAll(List.of(pagto1, pagto2));
	}
}




