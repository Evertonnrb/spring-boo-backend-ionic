package com.evertonnrb.mc1.service;

import com.evertonnrb.mc1.domain.Categoria;
import com.evertonnrb.mc1.repositories.CategoriaRepository;
import com.evertonnrb.mc1.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria buscar(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException(
                        "Recurso não encontrado id " + id + " tipo " + Categoria.class.getName()
                ));
    }

    public Categoria insert(Categoria categoria){
        categoria.setId(null);
        return repository.save(categoria);
    }
}
