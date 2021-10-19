package com.evertonnrb.mc1.service.validation;

import com.evertonnrb.mc1.domain.Cliente;
import com.evertonnrb.mc1.domain.dto.ClienteNewDTO;
import com.evertonnrb.mc1.domain.enuns.TipoCliente;
import com.evertonnrb.mc1.repositories.ClienteRepository;
import com.evertonnrb.mc1.service.exceptions.handler.FieldMessage;
import com.evertonnrb.mc1.service.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteNewDTO obj, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        if (obj.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(obj.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }
        if (obj.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(obj.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }
        Cliente aux = clienteRepository.findByEmail(obj.getEmail());
        if (aux.getEmail() != null) {
            list.add(new FieldMessage("email", "Email já cadastrado"));
        }
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
