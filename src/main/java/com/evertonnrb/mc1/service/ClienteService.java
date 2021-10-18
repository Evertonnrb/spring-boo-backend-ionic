package com.evertonnrb.mc1.service;

import com.evertonnrb.mc1.domain.Cidade;
import com.evertonnrb.mc1.domain.Cliente;
import com.evertonnrb.mc1.domain.Endereco;
import com.evertonnrb.mc1.domain.dto.ClienteDTO;
import com.evertonnrb.mc1.domain.dto.ClienteNewDTO;
import com.evertonnrb.mc1.domain.enuns.TipoCliente;
import com.evertonnrb.mc1.repositories.CidadeRepository;
import com.evertonnrb.mc1.repositories.ClienteRepository;
import com.evertonnrb.mc1.repositories.EnderecoRepository;
import com.evertonnrb.mc1.service.exceptions.DataIntegrityException;
import com.evertonnrb.mc1.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(
                () -> new ObjectNotFoundException(
                        "Recurso não encontrado id " + id + " tipo " + Cliente.class.getName()
                ));
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir entidades relacionadas");
        }

    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findByPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromCategoriaDto(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    public Cliente fromCategoriaDto(ClienteNewDTO clienteDTO) {
        Cliente cli = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getCpfOuCnpj(),
                TipoCliente.toEnun(clienteDTO.getTipo()));

        Cidade cidade = cidadeRepository.getOne(clienteDTO.getCidadeId());

        Endereco endereco = new Endereco(null, "Rua", "001",
                "nulo", "Jd city", "000000-00", cli, cidade);

        cli.getEnderecos().add(endereco);
        cli.getTelefones().add(clienteDTO.getTelefone1());
        if (clienteDTO.getTelefone2()!=null) cli.getTelefones().add(clienteDTO.getTelefone2());
        if (clienteDTO.getTelefone3()!=null) cli.getTelefones().add(clienteDTO.getTelefone3());
        return cli;
    }
}
