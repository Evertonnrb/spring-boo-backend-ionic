package com.evertonnrb.mc1.repositories;

import com.evertonnrb.mc1.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRespository extends JpaRepository<ItemPedido,Integer> {
}
