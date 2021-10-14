package com.evertonnrb.mc1;

import com.evertonnrb.mc1.domain.*;
import com.evertonnrb.mc1.domain.enuns.EstadoPagamento;
import com.evertonnrb.mc1.domain.enuns.TipoCliente;
import com.evertonnrb.mc1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class Mc1Application implements CommandLineRunner {

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
    private  ItemPedidoRespository itemPedidoRespository;

    public static void main(String[] args) {
        SpringApplication.run(Mc1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null,"Escritório");
        Categoria cat2 = new Categoria(null,"Contabilidade");

        Produto p1 = new Produto(null,"Computador",2000.00);
        Produto p2 = new Produto(null,"Impressora",1800.00);
        Produto p3 = new Produto(null,"Mouse",80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1,cat1));

        Estado rj = new Estado(null,"Rio de Janeiro");
        Estado sp = new Estado(null,"São Paulo");
        Estado ms = new Estado(null,"Mato Grosso do Sul");

        Cidade mc = new Cidade(null,"Macaé",rj);
        Cidade rio = new Cidade(null,"Rio",rj);

        Cidade spo = new Cidade(null,"São Paulo capital",sp);

        Cidade cg = new Cidade(null,"Campo Grande",ms);
        Cidade dr = new Cidade(null,"Dourados",ms);
        Cidade tlg = new Cidade(null,"Três Lagoas",ms);

        rj.getCidades().addAll(Arrays.asList(mc,rio));
        sp.getCidades().add(spo);
        ms.getCidades().addAll(Arrays.asList(cg,dr,tlg));

        Cliente maria = new Cliente(null,"Maria da Silva","mariasilva@gmail.com","12345678901", TipoCliente.PESSOAJURIDICA);

        maria.getTelefones().addAll(Arrays.asList("8899-2992","2033-3300"));

        Endereco endereco1 = new Endereco(null,"Rua 1","22","Casa dos fundos","JD IMA","23333332",maria,cg);
        Endereco endereco2 = new Endereco(null,"Rua das araras","1293","sem complemento","Inapolis","009223333",maria,tlg);

        maria.getEnderecos().addAll(Arrays.asList(endereco1,endereco2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido pedido1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),maria,endereco1 );
        Pedido pedido2 = new Pedido(null,sdf.parse("10/02/2018 00:01"),maria,endereco2 );

        Pagamento pagamento1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO,pedido1,3);
        pedido1.setPagamento(pagamento1);

        Pagamento pagamento2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,pedido2,sdf.parse("10/01/2018 00:00"),null);
        pedido2.setPagamento(pagamento2);

        maria.getPedidos().addAll(Arrays.asList(pedido1,pedido2));

        ItemPedido itemPedido1 = new ItemPedido(pedido1,p1,0.00,1,2000.00);
        ItemPedido itemPedido2 = new ItemPedido(pedido1,p3,0.00,2,80.00);
        ItemPedido itemPedido3 = new ItemPedido(pedido2,p2,100.00,1,800.00);

        pedido1.getItens().addAll(Arrays.asList(itemPedido1,itemPedido2));
        pedido2.getItens().addAll(Arrays.asList(itemPedido3));

        p1.getItens().addAll(Arrays.asList(itemPedido1));
        p2.getItens().addAll(Arrays.asList(itemPedido3));
        p3.getItens().addAll(Arrays.asList(itemPedido2));

        categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

        estadoRepository.saveAll(Arrays.asList(rj,sp,ms));
        cidadeRepository.saveAll(Arrays.asList(cg,dr,tlg,spo,mc,rio));

        clienteRepository.save(maria);

        enderecoRepository.saveAll(Arrays.asList(endereco1,endereco2));


        pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2));
        pagamentoRepository.saveAll(Arrays.asList(pagamento1,pagamento2));

        itemPedidoRespository.saveAll(Arrays.asList(itemPedido1,itemPedido2,itemPedido3));
    }
}
