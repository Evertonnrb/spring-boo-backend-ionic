package com.evertonnrb.mc1.service;

import com.evertonnrb.mc1.domain.Pedido;
import com.evertonnrb.mc1.repositories.PedidoRepository;
import com.evertonnrb.mc1.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido buscar(Integer id){
        Optional<Pedido> pedido = repository.findById(id);
        return pedido.orElseThrow(
                ()-> new ObjectNotFoundException("Recurso não encontrado : id = "+id+" tipo : "+Pedido.class.getName())
        );
    }
}
