package com.mariopimenta.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mariopimenta.cursomc.domain.Cliente;
import com.mariopimenta.cursomc.enums.Validacoes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = Validacoes.REQUIRED)
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message = Validacoes.REQUIRED)
	@Email(message = Validacoes.EMAIL)
	private String email;
	
	public ClienteDTO(Cliente obj) {
		this.setId(obj.getId());
		this.setNome(obj.getNome());
		this.setEmail(obj.getEmail());
	}
}
