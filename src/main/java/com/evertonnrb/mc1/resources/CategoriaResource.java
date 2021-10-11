package com.evertonnrb.mc1.resources;

import com.evertonnrb.mc1.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> oi(){

        Categoria info = new Categoria(1,"Informatica");
        Categoria lim = new Categoria(2,"Limpeza");
        Categoria escritorio = new Categoria(3,"Escritorio");
        Categoria promo = new Categoria(4,"Producao");
        Categoria geren= new Categoria(5,"Gerencia");

        List<Categoria> categorias = new ArrayList<>();
        categorias.add(info);
        categorias.add(lim);
        categorias.add(escritorio);
        categorias.add(promo);
        categorias.add(geren);
        return categorias;
    }
}
