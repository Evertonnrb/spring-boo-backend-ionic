package com.evertonnrb.mc1;

import com.evertonnrb.mc1.domain.Categoria;
import com.evertonnrb.mc1.domain.Cidade;
import com.evertonnrb.mc1.domain.Estado;
import com.evertonnrb.mc1.domain.Produto;
import com.evertonnrb.mc1.repositories.CategoriaRepository;
import com.evertonnrb.mc1.repositories.CidadeRepository;
import com.evertonnrb.mc1.repositories.EstadoRepository;
import com.evertonnrb.mc1.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

        categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

        estadoRepository.saveAll(Arrays.asList(rj,sp,ms));
        cidadeRepository.saveAll(Arrays.asList(cg,dr,tlg,spo,mc,rio));
    }
}
