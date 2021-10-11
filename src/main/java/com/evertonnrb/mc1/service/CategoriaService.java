package com.evertonnrb.mc1.service;

import com.evertonnrb.mc1.domain.Categoria;
import com.evertonnrb.mc1.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Optional<Categoria> buscar(Integer id){
        Optional<Categoria> one = repository.findById(id);
        return one;
    }
}
