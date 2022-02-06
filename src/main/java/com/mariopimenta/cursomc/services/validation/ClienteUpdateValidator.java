package com.mariopimenta.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.mariopimenta.cursomc.domain.Cliente;
import com.mariopimenta.cursomc.domain.enums.TipoCliente;
import com.mariopimenta.cursomc.dto.ClienteDTO;
import com.mariopimenta.cursomc.dto.ClienteNewDTO;
import com.mariopimenta.cursomc.repositories.ClienteRepository;
import com.mariopimenta.cursomc.resources.exceptions.FieldMessage;
import com.mariopimenta.cursomc.services.validation.utils.BR;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate constraintAnnotation) {}
	
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId =  Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) list.add(new FieldMessage("email", "Email já existente"));
		
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
