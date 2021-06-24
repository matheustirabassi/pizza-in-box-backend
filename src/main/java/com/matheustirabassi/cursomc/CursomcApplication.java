package com.matheustirabassi.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheustirabassi.cursomc.domain.Categoria;
import com.matheustirabassi.cursomc.domain.Cidade;
import com.matheustirabassi.cursomc.domain.Cliente;
import com.matheustirabassi.cursomc.domain.Endereco;
import com.matheustirabassi.cursomc.domain.Estado;
import com.matheustirabassi.cursomc.domain.Produto;
import com.matheustirabassi.cursomc.domain.enums.TipoCliente;
import com.matheustirabassi.cursomc.repositories.CategoriaRepository;
import com.matheustirabassi.cursomc.repositories.CidadeRepository;
import com.matheustirabassi.cursomc.repositories.ClienteRepository;
import com.matheustirabassi.cursomc.repositories.EnderecoRepository;
import com.matheustirabassi.cursomc.repositories.EstadoRepository;
import com.matheustirabassi.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Doces");

		Produto p1 = new Produto(null, "Bis", 4.59);
		Produto p2 = new Produto(null, "Diamante negro", 4.59);
		Produto p3 = new Produto(null, "Pc Gamer", 6.900);
		Produto p4 = new Produto(null, "Mesa de madeira", 400.00);

		cat3.getProdutos().addAll(Arrays.asList(p1, p2));
		cat1.getProdutos().addAll(Arrays.asList(p3));
		cat2.getProdutos().addAll(Arrays.asList(p4));

		p1.getCategorias().addAll(Arrays.asList(cat3));
		p2.getCategorias().addAll(Arrays.asList(cat3));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p4.getCategorias().addAll(Arrays.asList(cat2));

		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		Cidade c1 = new Cidade(null, "Cerquilho", est1);
		Cidade c2 = new Cidade(null, "Uberlândia", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2));
		
		Cliente cliente1 = new Cliente(null,"Matheus", "tirabassi.matheus@aluno.ifsp.edu.br","38384032810", TipoCliente.PESSOAFISICA);
		
		cliente1.getTelefones().addAll(Arrays.asList("15995125351", "15991097479"));
		
		Endereco e1 = new Endereco(null, "R Flores", "300", "Apto 300", "Jardina", "18520000", c1, cliente1);
		Endereco e2 = new Endereco(null, "Avenida Presidente Kenedy", "105", "Sala 800", "Centro", "14529-421", c2, cliente1);
		
		cliente1.getEnderecos().addAll(Arrays.asList(e1,e2));
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}

}
