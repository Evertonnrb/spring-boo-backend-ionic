package com.evertonnrb.mc1.domain.dto;

import com.evertonnrb.mc1.service.validation.ClienteInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ClienteInsert
public class ClienteNewDTO implements Serializable {

    @NotEmpty(message = "Campo obrigatório")
    @Length(min = 3, max = 120, message = "Campo obrigatório")
    private String nome;

    @NotEmpty(message = "Campo obrigatório")
    @Email(message = "Verifique o email informado")
    private String email;

    @NotEmpty(message = "Campo obrigatório")
    private String cpfOuCnpj;

    @NotNull(message = "Campo obrigatório")
    private Integer tipo;

    @NotEmpty(message = "Campo obrigatório")
    @Length(min = 1, max = 120, message = "Campo obrigatório")
    private String lagradouro;

    @NotEmpty(message = "Campo obrigatório")
    @Length(min = 1, max = 9, message = "Campo obrigatório")
    private String numero;

    private String complemento;

    @NotEmpty(message = "Campo obrigatório")
    @Length(min = 1, max = 120, message = "Campo obrigatório")
    private String bairro;

    @NotEmpty(message = "Campo obrigatório")
    private String cep;

    @NotEmpty(message = "Campo obrigatório")
    private String telefone1;

    private String telefone2;
    private String telefone3;

    private Integer cidadeId;

    public ClienteNewDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getLagradouro() {
        return lagradouro;
    }

    public void setLagradouro(String lagradouro) {
        this.lagradouro = lagradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }
}
