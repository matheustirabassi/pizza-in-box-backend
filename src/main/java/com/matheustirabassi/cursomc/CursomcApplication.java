package com.matheustirabassi.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
  public void run(String... args) throws Exception {}

}