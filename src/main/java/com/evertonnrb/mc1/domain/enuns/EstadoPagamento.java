package com.evertonnrb.mc1.domain.enuns;

public enum EstadoPagamento {

    PENDENTE (1,"Pendente"),
    QUITADO (2,"Quitado"),
    CANCELADO (3,"Cancelado");

    private int cod;
    private String descricao;

    EstadoPagamento(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCod() {
        return cod;
    }

    public static EstadoPagamento toEnun(Integer cod){
        if (cod == null){
            return null;
        }
        for (EstadoPagamento c : EstadoPagamento.values()){
            if (cod.equals(c.getCod())){
                return c;
            }
        }
        throw new IllegalArgumentException("ID inválido : "+cod);
    }
}
