package com.evertonnrb.mc1.service;

import com.evertonnrb.mc1.domain.ItemPedido;
import com.evertonnrb.mc1.domain.PagamentoComBoleto;
import com.evertonnrb.mc1.domain.Pedido;
import com.evertonnrb.mc1.domain.enuns.EstadoPagamento;
import com.evertonnrb.mc1.repositories.ItemPedidoRespository;
import com.evertonnrb.mc1.repositories.PagamentoRepository;
import com.evertonnrb.mc1.repositories.PedidoRepository;
import com.evertonnrb.mc1.repositories.ProdutoRepository;
import com.evertonnrb.mc1.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRespository itemPedidoRespository;

    public Pedido buscar(Integer id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(
                ()-> new ObjectNotFoundException("Recurso não encontrado : id = "+id+" tipo : "+Pedido.class.getName())
        );
    }

    @Transactional
    public Pedido insert(Pedido obj){
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto boleto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(boleto,obj.getInstante());
        }
        obj = pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());

        for(ItemPedido pedido : obj.getItens()){
            pedido.setDesconto(0.0);
            pedido.setPreco(produtoRepository.getOne(pedido.getProduto().getId()).getPreco());
            pedido.setPedido(obj);
        }
        // itemPedidoRespository.save(obj.getItens());
        return obj;
    }
}
