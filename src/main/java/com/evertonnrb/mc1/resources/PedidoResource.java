package com.evertonnrb.mc1.resources;

import com.evertonnrb.mc1.domain.Pedido;
import com.evertonnrb.mc1.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find (@PathVariable Integer id){
        Pedido pedido = service.buscar(id);
        return ResponseEntity.ok().body(pedido);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(@Valid @RequestBody Pedido obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
