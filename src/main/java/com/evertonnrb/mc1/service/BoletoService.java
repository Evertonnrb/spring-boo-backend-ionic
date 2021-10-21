package com.evertonnrb.mc1.service;

import com.evertonnrb.mc1.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, Date instPedido){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instPedido);
        calendar.add(Calendar.DAY_OF_MONTH,7);
    }

}
