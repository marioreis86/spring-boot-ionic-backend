package com.mariopimenta.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.mariopimenta.cursomc.enums.Validacoes;
import com.mariopimenta.cursomc.services.validation.ClienteInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@ClienteInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = Validacoes.REQUIRED)
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message = Validacoes.REQUIRED)
	@Email(message = Validacoes.EMAIL)
	private String email;
	
	@NotEmpty(message = Validacoes.REQUIRED)
	private String cpfOuCnpj;
	private Integer tipo;
	
	@NotEmpty(message = Validacoes.REQUIRED)
	private String logradouro;
	
	@NotEmpty(message = Validacoes.REQUIRED)
	private String numero;
	
	private String complemento;
	private String bairro;
	
	@NotEmpty(message = Validacoes.REQUIRED)
	private String cep;
	
	@NotEmpty(message = Validacoes.REQUIRED)
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;
}
