package com.evertonnrb.mc1.service;

import com.evertonnrb.mc1.domain.Categoria;
import com.evertonnrb.mc1.domain.Cliente;
import com.evertonnrb.mc1.repositories.ClienteRepository;
import com.evertonnrb.mc1.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscar(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(
                () -> new ObjectNotFoundException(
                        "Recurso não encontrado id " + id + " tipo " + Cliente.class.getName()
                ));
    }
}
