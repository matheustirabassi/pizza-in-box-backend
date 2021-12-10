package com.matheustirabassi.cursomc;

import java.text.SimpleDateFormat;
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
import com.matheustirabassi.cursomc.domain.ItemPedido;
import com.matheustirabassi.cursomc.domain.Pagamento;
import com.matheustirabassi.cursomc.domain.PagamentoComBoleto;
import com.matheustirabassi.cursomc.domain.PagamentoComCartao;
import com.matheustirabassi.cursomc.domain.Pedido;
import com.matheustirabassi.cursomc.domain.Produto;
import com.matheustirabassi.cursomc.domain.enums.EstadoPagamento;
import com.matheustirabassi.cursomc.domain.enums.StatusPermissao;
import com.matheustirabassi.cursomc.domain.enums.TipoCliente;
import com.matheustirabassi.cursomc.repositories.CategoriaRepository;
import com.matheustirabassi.cursomc.repositories.CidadeRepository;
import com.matheustirabassi.cursomc.repositories.ClienteRepository;
import com.matheustirabassi.cursomc.repositories.EnderecoRepository;
import com.matheustirabassi.cursomc.repositories.EstadoRepository;
import com.matheustirabassi.cursomc.repositories.ItemPedidoRepository;
import com.matheustirabassi.cursomc.repositories.PagamentoRepository;
import com.matheustirabassi.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
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

		Cliente cli1 = new Cliente(null, "Matheus", "tirabassi.matheus@aluno.ifsp.edu.br", "38384032810",
				TipoCliente.PESSOAFISICA);
		cli1.setStatusPermissao(StatusPermissao.ADMINISTRADOR);

		cli1.getTelefones().addAll(Arrays.asList("15995125351", "15991097479"));

		Endereco e1 = new Endereco(null, "R Flores", "300", "Apto 300", "Jardina", "18520000", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Presidente Kenedy", "105", "Sala 800", "Centro", "14529-421", c2,
				cli1);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.0);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
