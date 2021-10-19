package com.evertonnrb.mc1.service;

import com.evertonnrb.mc1.domain.Categoria;
import com.evertonnrb.mc1.domain.Pedido;
import com.evertonnrb.mc1.domain.Produto;
import com.evertonnrb.mc1.repositories.CategoriaRepository;
import com.evertonnrb.mc1.repositories.ProdutoRepository;
import com.evertonnrb.mc1.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElseThrow(
                ()-> new ObjectNotFoundException("Recurso não encontrado : id = "+id+" tipo : "+Pedido.class.getName())
        );
    }

    public Page<Produto> search(String nome, List<Integer> ids,Integer page,Integer linesPerPage,
                                String orderBy, String direction){
        PageRequest request = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.search(nome,categorias,request);
    }
}
