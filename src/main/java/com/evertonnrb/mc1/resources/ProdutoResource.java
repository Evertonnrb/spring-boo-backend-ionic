package com.evertonnrb.mc1.resources;

import com.evertonnrb.mc1.domain.Cliente;
import com.evertonnrb.mc1.domain.Pedido;
import com.evertonnrb.mc1.domain.Produto;
import com.evertonnrb.mc1.domain.dto.ClienteDTO;
import com.evertonnrb.mc1.domain.dto.ProdutoDTO;
import com.evertonnrb.mc1.resources.utils.URLUtils;
import com.evertonnrb.mc1.service.PedidoService;
import com.evertonnrb.mc1.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Produto produto = service.find(id);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findPage(
            @RequestParam(value = "nome",defaultValue = "") String nome,
            @RequestParam(value = "categorias",defaultValue = "") String categorias,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nomeDecoded = URLUtils.decodeParam(nome);
        List<Integer> ids = URLUtils.decodeIntList(categorias);
        Page<Produto> list = service.search(nomeDecoded,ids,page,linesPerPage,orderBy,direction);
        Page<ProdutoDTO> dtoList =list.map( ProdutoDTO::new) ;
        return ResponseEntity.ok().body(dtoList);
    }
}
