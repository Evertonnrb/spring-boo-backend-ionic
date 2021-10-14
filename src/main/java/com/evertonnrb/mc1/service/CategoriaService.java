package com.evertonnrb.mc1.service;

import com.evertonnrb.mc1.domain.Categoria;
import com.evertonnrb.mc1.repositories.CategoriaRepository;
import com.evertonnrb.mc1.service.exceptions.DataIntegrityException;
import com.evertonnrb.mc1.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException(
                        "Recurso não encontrado id " + id + " tipo " + Categoria.class.getName()
                ));
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return repository.save(categoria);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return repository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir ");
        }

    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Page<Categoria> findByPage(Integer page,Integer linesPerPage, String orderBy,String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repository.findAll(pageRequest);
    }
}
