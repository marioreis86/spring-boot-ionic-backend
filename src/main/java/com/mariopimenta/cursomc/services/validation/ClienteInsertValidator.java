package com.mariopimenta.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mariopimenta.cursomc.domain.Cliente;
import com.mariopimenta.cursomc.domain.enums.TipoCliente;
import com.mariopimenta.cursomc.dto.ClienteNewDTO;
import com.mariopimenta.cursomc.repositories.ClienteRepository;
import com.mariopimenta.cursomc.resources.exceptions.FieldMessage;
import com.mariopimenta.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert constraintAnnotation) {}
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) 
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) 
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) list.add(new FieldMessage("email", "Email já existente"));
		
		//ASSOCIA ERROS LOCAIS COM O FRAMEWORK
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
				.addPropertyNode(e.getFieldName())
				.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
